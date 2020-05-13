package com.spring.adminlte.resController.admin;

import com.spring.adminlte.component.Translator;
import com.spring.adminlte.constants.SYN;
import com.spring.adminlte.constants.Status;
import com.spring.adminlte.dao.ProductDetailsDao;
import com.spring.adminlte.dto.*;
import com.spring.adminlte.dto.vo.ListIDDto;
import com.spring.adminlte.dto.vo.ProductDetailsListDto;
import com.spring.adminlte.templatesDto.*;
import com.spring.adminlte.services.serviceImplements.ProductServiceImplement;
import com.spring.adminlte.utils.SystemUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @CreateDate 20/03/2020
 * @Author Ean Dalin
 * @API product
 **/
@RestController
@RequestMapping(value = "api/product")
public class ProductRes {
    private static final Logger log = LoggerFactory.getLogger(CompanyRes.class);
    @Autowired
    private ProductServiceImplement productService;
    @Autowired
    private ProductDetailsDao productDetailsDao;
    @Autowired
    private PlatformTransactionManager transactionManager;

    String msg = "";
    /*
    * @functionName getProductDetailsList
    * @param param
    * @description get product details list
    * */
    @PostMapping(value = "/getProductDetailsList")
    public ResponseEntity<ResponseData<ProductDetailsListDto>> getProductDetailsList(@RequestBody RequestData<HeaderDto> param) {
        ResponseData<ProductDetailsListDto> response = new ResponseData();
        HeaderDto header = param.getHeader();
        ProductDetailsListDto responseList = new ProductDetailsListDto();
        try{
            System.out.println(SystemUtil.projectPath());

            List<ProductDetailsDto> list = productDetailsDao.getList(Status.Delete.getValueStr());
            responseList.setList(list);
            response.setHeader(header);
            response.setBody(responseList);
        }catch (Exception e) {
            log.error("\n ===> get error get product details list\n", e.getMessage());
            throw e;
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * @functionName getProductList
     * @param  param
     * @description get product list
     * @return list of product
     **/
    @PostMapping(value = "/getProductList")
    public ResponseEntity<ResponseData<List<ProductDto>>> getProductList(@RequestBody RequestData<HeaderDto> param) {
        ResponseData<List<ProductDto>> response = new ResponseData<>();
        HeaderDto header = param.getHeader();
        try {
            List<ProductDto> list = productService.getList();
            response.setBody(list);
            response.setHeader(header);
        }catch (Exception e ) {
            log.error("\n get error api get product list\n", e.getMessage());
            throw e;
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * @functionName update
     * @param  param
     * @description update product information to data base
     * @return YN
     **/
    @PostMapping(value = "/save")
    public ResponseEntity<ResponseData<ReturnYNDto>> save(@RequestBody RequestData<ProductDto> param) {
        ResponseData<ReturnYNDto> response = new ResponseData<>();
        ProductDto product      = param.getBody();
        HeaderDto header        = param.getHeader();
        try {
            Long save = productService.update(product);
            if (save > 0) {
                response.setBody(new ReturnYNDto(true, msg));
                response.setHeader(header);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }catch (Exception e) {
            log.error("\n get error api save product\n", e.getMessage());
            throw e;
        }

        header.setMsg(msg);
        header.setResult(false);

        response.setBody(new ReturnYNDto(false, msg));
        response.setHeader(header);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    /**
     * @functionName save
     * @param  param
     * @description save product information to data base
     * @return YN
     **/
    @PostMapping(value = "/update")
    public ResponseEntity<ResponseData<ReturnYNDto>> update(@RequestBody RequestData<ProductDto> param) {
        ResponseData<ReturnYNDto> response = new ResponseData<>();
        ProductDto product      = param.getBody();
        HeaderDto header        = param.getHeader();
        try {
            Long save = productService.save(product);
            if (save > 0) {
                response.setBody(new ReturnYNDto(true, msg));
                response.setHeader(header);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }catch (Exception e) {
            log.error("\n get error api save product\n", e.getMessage());
            throw e;
        }

        header.setMsg(msg);
        header.setResult(false);

        response.setBody(new ReturnYNDto(false, msg));
        response.setHeader(header);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * @functionName deleteByListId
     * @param  param
     * @description get delete sub category by list of iD
     * @return response
     **/
    @PostMapping(value = "/deleteByListID")
    public ResponseEntity<ResponseData<ReturnYNDto>> deleteByListId (@RequestBody RequestData<ListIDDto> param) {
        ResponseData<ReturnYNDto> response         = new ResponseData<>();
        HeaderDto header                = param.getHeader();
        ProductDto product              = new ProductDto();
        ListIDDto       listIDDto       = param.getBody();
        TransactionStatus transactionStatus    = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try{
            if (listIDDto.getList().size() > 0) {
                for ( IdDto idDto: listIDDto.getList()) {
                    product.setProductId(idDto.getId());
                    product.setStatus(Status.Delete.getValueStr());
                    productService.delete(product);
                }
                transactionManager.commit(transactionStatus);
                header.setResult(true);
                response.setHeader(header);
                response.setBody(new ReturnYNDto(true, SYN.Y.getValue()));
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

        }catch (Exception e) {
            log.error("\n get error api sub category save error:\n" + e.getMessage());
            msg = Translator.toLocale( header.getLanguageCode(),"GET_ERROR_EXCEPTION");
            transactionManager.rollback(transactionStatus);
            throw e;
        }

        header.setResult(false);
        header.setMsg(msg);
        response.setHeader(header);
        response.setBody(new ReturnYNDto(false, SYN.N.getValue()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private Boolean isValid(String langaugeCode, ProductDto product) {
        if (product.getProductName() == null | product.getProductName().equals("")) {
            msg = Translator.toLocale(langaugeCode, "Product_Name_Null");
            return false;
        }
        if (product.getSubCategoryId() == 0) {
            msg = Translator.toLocale(langaugeCode, "Product_SubCate");
            return false;
        }
        return true;
    }
}