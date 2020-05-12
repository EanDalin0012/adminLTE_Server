/*
* @author Ean dlin
* @description import product api
* @date 18-04-2020
* */
package com.spring.adminlte.resController.admin;

import com.spring.adminlte.component.Translator;
import com.spring.adminlte.constants.SYN;
import com.spring.adminlte.constants.Status;
import com.spring.adminlte.dao.ImportProductDetailsDao;
import com.spring.adminlte.dto.HeaderDto;
import com.spring.adminlte.dto.ImportProductDetailsDto;
import com.spring.adminlte.dto.ImportProductDto;
import com.spring.adminlte.dto.ReturnYNDto;
import com.spring.adminlte.dto.vo.ImportProductDetailsListDto;
import com.spring.adminlte.dto.vo.ImportProductListDto;
import com.spring.adminlte.services.serviceImplements.ImportProductServiceImplement;
import com.spring.adminlte.templatesDto.RequestData;
import com.spring.adminlte.templatesDto.ResponseData;
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

@RestController
@RequestMapping(value = "/api/import-product")
public class ImportProductRes {
    private static final Logger log = LoggerFactory.getLogger(ImportProductRes.class);

    @Autowired
    private ImportProductServiceImplement importProductService;
    @Autowired
    private ImportProductDetailsDao importProductDetailsDao;
    @Autowired
    private PlatformTransactionManager transactionManager;

    String msg = "";

    /*
    * @functionName inquiryImportPrdoductDetials
    * @param param
    * @decription inquiry data details of import product
    * */
    @PostMapping(value = "/getListDetails")
    public ResponseEntity<ResponseData<ImportProductDetailsListDto>> inquiryImportProductDetails(@RequestBody RequestData<HeaderDto> param) {
        ResponseData<ImportProductDetailsListDto> response = new ResponseData<>();
        HeaderDto header = param.getHeader();
        ImportProductDetailsListDto responseList = new ImportProductDetailsListDto();
        try{
            List<ImportProductDetailsDto> list = importProductDetailsDao.getList(Status.Delete.getValueStr());
            responseList.setList(list);
            response.setBody(responseList);
            response.setHeader(header);
        }catch (Exception e) {
            log.error("\n ====> get error inquiry import product details <=== \n");
            throw  e;
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    /*
    *@functionName getList()
    * @param param
    * @description get list of import product
    * */
    @PostMapping(value = "/getList")
    public ResponseEntity<ResponseData<ImportProductListDto>> getList(@RequestBody RequestData<HeaderDto> param) {
        ResponseData<ImportProductListDto> response = new ResponseData<>();
        HeaderDto header = param.getHeader();
        ImportProductListDto responseList = new ImportProductListDto();
        try{
            List<ImportProductDto> list = importProductService.getList(Status.Delete.getValueStr());
            responseList.setList(list);
            response.setHeader(header);
            response.setBody(responseList);
        }catch (Exception e) {
            log.error("\n ===>> get error get list of import product <===\n", e.getMessage());
            throw e;
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    /*
    * @functionName save
    * @param requestData
    * @description  save import product information
    * */
    @PostMapping(value = "/save")
    public ResponseEntity<ResponseData<ReturnYNDto>> save (@RequestBody RequestData<ImportProductListDto> requestData) {
        ResponseData<ReturnYNDto> response = new ResponseData<>();
        HeaderDto header = requestData.getHeader();
        ImportProductListDto list = requestData.getBody();
        TransactionStatus transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
        Long save = null;

        try {
            for ( ImportProductDto trmImp : list.getList() ) {
                if (isValid(trmImp, header.getLanguageCode()) == true) {
                    trmImp.setStatus(Status.Active.getValueStr());
                    save = importProductService.save(trmImp);
                }
            }
            if ( save > 0) {
                transactionManager.commit(transactionStatus);
                response.setHeader(header);
                response.setBody( new ReturnYNDto(false, SYN.Y.getKey()));
                return new ResponseEntity<>(response, HttpStatus.OK);
            }

        }catch (Exception e) {
            transactionManager.rollback(transactionStatus);
            throw e;
        }

        header.setResult(false);
        header.setMsg(msg);

        response.setHeader(header);
        response.setBody( new ReturnYNDto(false, SYN.N.getKey()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private  boolean isValid(ImportProductDto importProductDto, String languageCode) {
        if (importProductDto.getProductId() == 0) {
            msg = Translator.toLocale(languageCode, "");
            return false;
        }
        if (importProductDto.getCompanyId() == 0) {
            msg = Translator.toLocale(languageCode, "");
            return  false;
        }
        if (importProductDto.getQuantity() == 0) {
            msg = Translator.toLocale(languageCode, "");
            return  false;
        }
        if (importProductDto.getPrice() == 0) {
            msg = Translator.toLocale(languageCode, "");
            return  false;
        }
        if (importProductDto.getTotal() == 0) {
            msg = Translator.toLocale(languageCode, "");
            return  false;
        }
        if (importProductDto.getSupplierId() == 0) {
            msg = Translator.toLocale(languageCode, "");
            return  false;
        }

        return  true;
    }
}
