/**
 * @CreateDate 08/03/2020
 * @Author Ean Dalin
 * @API sub category
 **/
package com.spring.adminlte.resController.admin;

import com.spring.adminlte.component.Translator;
import com.spring.adminlte.constants.SYN;
import com.spring.adminlte.constants.Status;
import com.spring.adminlte.dao.SubCategoryDetailDao;
import com.spring.adminlte.dto.*;
import com.spring.adminlte.dto.vo.ListIDDto;
import com.spring.adminlte.dto.vo.SubCategoryListDto;
import com.spring.adminlte.templatesDto.*;
import com.spring.adminlte.services.serviceImplements.SubCategoryServiceImplement;
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
@RequestMapping(value = "api/sub_category")
public class SubCategoryRes {
    private static final Logger log = LoggerFactory.getLogger(CompanyRes.class);
    @Autowired
    private SubCategoryServiceImplement subCategoryService;
    @Autowired
    private SubCategoryDetailDao subCategoryDetailDao;
    @Autowired
    private PlatformTransactionManager transactionManager;

    String msg = "";
    /**
     * @functionName getList
     * @param  param
     * @description get sub category list
     * @return list of sub category
     **/
    @PostMapping(value = "/getList")
    public ResponseEntity<ResponseData<List<SubCategoryDetailDto>>> getList(@RequestBody RequestData<HeaderDto> param) {
        ResponseData<List<SubCategoryDetailDto>> response = new ResponseData<>();
        HeaderDto header                          = param.getHeader();
        try{
            List<SubCategoryDetailDto> list = subCategoryDetailDao.getList();
            response.setHeader(header);
            response.setBody(list);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e) {
            log.error("\n get error api sub category get list:\n"+e.getMessage());
            throw e;
        }

    }

    /*
    * @functionName subCategoryList
    * @param param
    * @description get all list of subcategory that not delete
    * */
    @PostMapping(value = "/subCategoryList")
    public ResponseEntity<ResponseData<SubCategoryListDto>> subCategoryList(@RequestBody RequestData<HeaderDto> param) {
        ResponseData<SubCategoryListDto> response = new ResponseData<>();
        SubCategoryListDto  responseList = new SubCategoryListDto();
        HeaderDto header = param.getHeader();
            try{
                    List<SubCategoryDto> list = subCategoryService.getList(Status.Delete.getValueStr());
                    responseList.setList(list);
                    response.setHeader(header);
                    response.setBody(responseList);
            }catch (Exception e) {
                log.error("\n get error subcategory list \n", e.getMessage());
                throw e;
            }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * @functionName getList
     * @param  param
     * @description get sub category list
     * @return list of sub category
     **/
    @PostMapping(value = "/getSubCategoryList")
    public ResponseEntity<ResponseData<List<SubCategoryDetailDto>>> getSubCategoryList(@RequestBody RequestData<SubCategoryDto> param) {
        ResponseData<List<SubCategoryDetailDto>> response = new ResponseData<>();
        HeaderDto header                   = param.getHeader();
        try{
            List<SubCategoryDetailDto> list = subCategoryDetailDao.getList();
            response.setHeader(header);
            response.setBody(list);
        }catch (Exception e) {
            log.error("\n get error api sub category get list:\n", e.getMessage());
            header.setResult(false);
            header.setMsg(Translator.toLocale( header.getLanguageCode(),"GET_ERROR_EXCEPTION"));
            throw e;
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * @functionName save
     * @param  param
     * @description get save information sub category
     * @return ResponseMapDto
     **/
    @PostMapping(value = "/save")
    public ResponseEntity<ResponseData<ReturnYNDto>> save (@RequestBody RequestData<SubCategoryDto> param) {
        ResponseData<ReturnYNDto> response = new ResponseData<>();
        HeaderDto header        = param.getHeader();
        SubCategoryDto body     = param.getBody();
        try{
            if (isValid(body, header.getLanguageCode()) == true) {
                body.setStatus(Status.Active.getValueStr());
                Long save = subCategoryService.save(body);
                if (save > 0 ) {
                    header.setResult(true);
                    response.setHeader(header);
                    response.setBody(new ReturnYNDto(true, SYN.Y.getValue()));
                    return new ResponseEntity<>(response,HttpStatus.OK);
                }
            }
        }catch (Exception e) {
            log.error("\n get error api sub category save error:\n" + e.getMessage());
            msg = Translator.toLocale( header.getLanguageCode(),"GET_ERROR_EXCEPTION");
            throw e;
        }

        header.setResult(false);
        header.setMsg(msg);
        response.setHeader(header);
        response.setBody(new ReturnYNDto(false, SYN.N.getValue()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * @functionName update
     * @param  param
     * @description get update information sub category
     * @return ResponseMapDto
     **/
    @PostMapping(value = "/update")
    public ResponseEntity<ResponseData<ReturnYNDto>> update (@RequestBody RequestData<SubCategoryDto> param) {
        ResponseData<ReturnYNDto> response = new ResponseData<>();
        HeaderDto header        = param.getHeader();
        SubCategoryDto body     = param.getBody();
        try{
            if (isValidForUdate(body, header.getLanguageCode()) == true) {
                body.setStatus(Status.Modify.getValueStr());
                Long update = subCategoryService.update(body);
                if (update > 0 ) {
                    header.setResult(true);
                    response.setHeader(header);
                    response.setBody(new ReturnYNDto(true, SYN.Y.getValue()));
                    return new ResponseEntity<>(response,HttpStatus.OK);
                }
            }
        }catch (Exception e) {
            log.error("\n get error api sub category save error:\n" + e.getMessage());
            msg = Translator.toLocale( header.getLanguageCode(),"GET_ERROR_EXCEPTION");
            throw e;
        }

        header.setResult(false);
        header.setMsg(msg);
        response.setHeader(header);
        response.setBody(new ReturnYNDto(false, SYN.N.getValue()));
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
        ResponseData<ReturnYNDto> response = new ResponseData<>();
        HeaderDto header                = param.getHeader();
        SubCategoryDto subCategoryDto   = new SubCategoryDto();
        ListIDDto listIDDto             = param.getBody();
        TransactionStatus transactionStatus    = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try{
            if (listIDDto.getList().size() > 0) {
                for ( IdDto idDto: listIDDto.getList()) {
                    subCategoryDto.setId(idDto.getId());
                    subCategoryDto.setStatus(Status.Delete.getValueStr());
                    subCategoryService.delete(subCategoryDto);
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

    /**
     * @functionName isValid
     * @param subCategory,languageCode
     * @description check valid information of sub category
     * @return true, false
     */
    private boolean isValid(SubCategoryDto subCategory, String languageCode) {
        if (subCategory.getMainCatId() == 0) {
            msg = Translator.toLocale( languageCode,"GET_ERROR_MAIN_CATEGORY_ID");
            return false;
        } else  if (subCategory.getCategoryName() == "" || subCategory.getCategoryName() == null) {
            msg = Translator.toLocale( languageCode,"SUB_CATEGORY_REQUIRED");
            return false;
        }
        return true;
    }

    /**
     * @functionName isValid
     * @param subCategory,languageCode
     * @description check valid information of sub category
     * @return true, false
     */
    private boolean isValidForUdate(SubCategoryDto subCategory, String languageCode) {
        if (subCategory.getId() == 0) {
            msg = Translator.toLocale( languageCode,"GET_ERROR_MAIN_CATEGORY_ID");
            return false;
        } else if(subCategory.getMainCatId() == 0) {
            msg = Translator.toLocale( languageCode,"GET_ERROR_MAIN_CATEGORY_ID");
            return false;
        } else  if (subCategory.getCategoryName() == "" || subCategory.getCategoryName() == null) {
            msg = Translator.toLocale( languageCode,"SUB_CATEGORY_REQUIRED");
            return false;
        }
        return true;
    }
}
