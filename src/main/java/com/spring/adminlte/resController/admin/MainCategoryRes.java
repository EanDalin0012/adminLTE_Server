/**
 * @CreateDate 02/03/2020
 * @Author Ean Dalin
 * @API Main Category
 **/
package com.spring.adminlte.resController.admin;

import com.spring.adminlte.component.Translator;
import com.spring.adminlte.constants.SYN;
import com.spring.adminlte.constants.Status;
import com.spring.adminlte.dto.*;
import com.spring.adminlte.dto.vo.ListIDDto;
import com.spring.adminlte.templatesDto.*;
import com.spring.adminlte.services.serviceImplements.MainCategoryServiceImplement;
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
@RequestMapping(value = "api/main_category")
public class MainCategoryRes {
    private static final Logger log = LoggerFactory.getLogger(CompanyRes.class);

    @Autowired
    private MainCategoryServiceImplement mainCategoryService;
    @Autowired
    private PlatformTransactionManager transactionManager;

    String msg = "";

    /**
     * @param param
     * @functionName getList
     * @description get main category list
     **/
    @PostMapping(value = "/getList")
    public ResponseEntity<ResponseData<List<MainCategoryDto>>> getList(@RequestBody RequestData<HeaderDto> param) {
        ResponseData<List<MainCategoryDto>> response = new ResponseData<>();
        HeaderDto headerDto                        = param.getHeader();
        try{
            List<MainCategoryDto> list = mainCategoryService.getList(Status.Active.getValueStr());
            headerDto.setResult(true);
            response.setHeader(headerDto);
            response.setBody(list);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e) {
            log.error("\nget Error api main category list <<<=== getList() ===>>>:\n", e.getMessage());
            msg = "GET_ERROR_EXCEPTION";
           throw e;
        }
    }

    /**
     * @param param
     * @functionaName save
     * @description save main category information
     **/
    @PostMapping(value = "/save")
    public ResponseEntity<ResponseData<ReturnYNDto>> save(@RequestBody RequestData<MainCategoryDto> param) {
        ResponseData<ReturnYNDto> response = new ResponseData<>();
        HeaderDto header        = param.getHeader();
        MainCategoryDto body    = param.getBody();

        try{
            if (isValid(body, header.getLanguageCode()) == true ) {
                String status = Status.Active.getValueStr();
                body.setStatus(status);
                body.setCreateBy(1);
                Long save = mainCategoryService.save(body);
                if (save > 0) {
                    response.setBody(new ReturnYNDto(true, SYN.Y.getValue()));
                    response.setHeader(header);
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
            }
        }catch (Exception e) {
            log.error("\nget Error api main category ===>>>:", e.getMessage());
           throw e;
        }

        header.setResult(false);
        response.setHeader(header);
        response.setBody(new ReturnYNDto(false, SYN.N.getValue()));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    /**
     * @param param
     * @functionName Update
     * @description update Main Category
     **/
    @PostMapping(value = "/update")
    public ResponseEntity<ResponseData<ReturnYNDto>> update(@RequestBody RequestData<MainCategoryDto> param) {
        ResponseData<ReturnYNDto> response = new ResponseData<>();
        HeaderDto header        = param.getHeader();
        MainCategoryDto body    = param.getBody();

        try{
            if (isValid(body, header.getLanguageCode()) == true ) {
                body.setStatus(Status.Modify.getValueStr());
                body.setCreateBy(1);
                Long update = mainCategoryService.update(body);
                if (update > 0) {
                    response.setBody(new ReturnYNDto(true, SYN.Y.getValue()));
                    response.setHeader(header);
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
            }
        }catch (Exception e) {
            log.error("\n get error api main category update ===>>>:", e.getMessage());
           throw e;
        }

        header.setResult(false);
        response.setHeader(header);
        response.setBody(new ReturnYNDto(false, SYN.N.getValue()));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * @param param
     * @functionName deleteByIDList
     * @description delete main category by list of ID
     **/
    @PostMapping(value = "/deleteByListId")
    public ResponseEntity<ResponseData<ReturnYNDto>> deleteByIDList(@RequestBody RequestData<ListIDDto> param) {
        ResponseData<ReturnYNDto> response  = new ResponseData<>();
        HeaderDto header                    = param.getHeader();
        ListIDDto       listId                     = param.getBody();
        MainCategoryDto mainCategoryDto     = new MainCategoryDto();
        TransactionStatus transactionStatus    = transactionManager.getTransaction(new DefaultTransactionDefinition());

        try{
            if(listId.getList().size() > 0) {
                for ( IdDto idDto: listId.getList()) {
                    if (idDto.getId() != 0) {
                        mainCategoryDto.setId(idDto.getId());
                        mainCategoryDto.setStatus(Status.Delete.getValueStr());
                        Long delete = mainCategoryService.delete(mainCategoryDto);
                    }
                }

                transactionManager.commit(transactionStatus);
                response.setHeader(header);
                response.setBody(new ReturnYNDto(true, SYN.Y.getValue()));
                return new ResponseEntity<>(response, HttpStatus.OK);

            }
        }catch (Exception e) {
            log.error("\n get error category get list by Id ===>>>:", e.getMessage());
            transactionManager.rollback(transactionStatus);
           throw  e;
        }

        msg = Translator.toLocale( header.getLanguageCode(),"ID_LIST_ERROR");
        header.setResult(false);
        header.setMsg(msg);
        response.setHeader(header);
        response.setBody(new ReturnYNDto(true, SYN.Y.getValue()));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * @param mainCategoryDto,LanguageCode
     * @functionName isValid
     *
     * @description Check valid data from client side
     **/
    private boolean isValid(MainCategoryDto mainCategoryDto, String LanguageCode) {
        if (mainCategoryDto.getMainCategoryName().trim() == "" || mainCategoryDto.getMainCategoryName() == null) {
            msg = Translator.toLocale( LanguageCode,"Main_Category_Name_Required");
            return false;
        }
        return true;
    }

    }
