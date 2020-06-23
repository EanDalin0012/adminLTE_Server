/**
 * @CreateDate 02/03/2020
 * @Author Ean Dalin
 * @API Main Category
 **/

package com.spring.adminlte.api.restcontroller.admin;

import com.spring.adminlte.component.Translator;
import com.spring.adminlte.constants.SYN;
import com.spring.adminlte.constants.Status;
import com.spring.adminlte.core.template.classes.DataResponse;
import com.spring.adminlte.core.template.classes.RequestData;
import com.spring.adminlte.dto.*;
import com.spring.adminlte.dto.vo.IDVo;
import com.spring.adminlte.services.serviceImplements.CompanyServiceImplement;
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
@RequestMapping(value = "/api/company_access")
public class CompanyRestController {

    private static final Logger log = LoggerFactory.getLogger(CompanyRestController.class);

    @Autowired
    private CompanyServiceImplement companyService;
    @Autowired
    private PlatformTransactionManager transactionManager;

    String msg;

    /**
     * @param param
     * @functionName getList
     * @description Get List Company
     **/
    @PostMapping(value = "/getList")
    public ResponseEntity<DataResponse<List<CompanyDto>>> getList(@RequestBody RequestData<HeaderDto> param) {
        DataResponse<List<CompanyDto>> response = new DataResponse<>();
        HeaderDto headerDto                   = param.getHeader();
        try{
            List<CompanyDto> list = companyService.getList(Status.Delete.getValueStr());
            response.setHeader(headerDto);
            response.setBody(list);
        }catch (Exception e) {
            log.error("\n get error api company <<<=== getList() ===>>:\n", e.getMessage());
            throw  e;
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * @param parma
     * @functionName save
     * @description save company information
     **/
    @PostMapping(value = "/save")
    public ResponseEntity<DataResponse<ReturnYNDto>> save(@RequestBody RequestData<CompanyDto> parma) {
        DataResponse<ReturnYNDto> response = new DataResponse<>();
        HeaderDto header        = parma.getHeader();
        CompanyDto body         = parma.getBody();

        response.setHeader(header);
        try {
            if( isValid(body, header.getLanguageCode() ) ) {
                body.setStatus(Status.Active.getValueStr());
                System.out.println(body.toString());
                Long save = companyService.save(body);
                if (save > 0) {
                    response.setBody(new ReturnYNDto(true, SYN.Y.getValue()));
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
            } else {
                header.setResult(false);
                header.setMsg(msg);
                response.setBody(new ReturnYNDto(false, SYN.N.getValue()));
                return new ResponseEntity<>(response,HttpStatus.OK);
            }

        }catch (Exception e) {
            log.error("\n get error api save company Error <<<=== save() ===>>>:" + e.getMessage());
            throw e;
        }
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    /**
     * @param param
     * @functionName update
     * @description update company information
     **/
    @PostMapping(value = "/update")
    public ResponseEntity<DataResponse<ReturnYNDto>> update (@RequestBody RequestData<CompanyDto> param) {
        DataResponse<ReturnYNDto> response  = new DataResponse<>();
        HeaderDto header         = param.getHeader();
        CompanyDto body          = param.getBody();

        response.setHeader(header);
        try {
            if (isValid(body, header.getLanguageCode())) {
                body.setStatus(Status.Modify.getValueStr());
                Long update = companyService.update(body);
                if (update > 0) {
                    response.setBody(new ReturnYNDto(true, SYN.Y.getValue()));
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
            }
        } catch (Exception e) {
            log.error("\n get error company api Error <<<=== update() ===>>>:\n", e.getMessage());
            throw  e;
        }

        header.setResult(false);
        response.setHeader(header);
        response.setBody(new ReturnYNDto(false, SYN.N.getValue()));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * @param param
     * @functionName delete
     * @description delete main category by id list
     **/
    @PostMapping(value = "/updateListByID")
    public ResponseEntity<DataResponse<ReturnYNDto>> delete(@RequestBody RequestData<IDVo> param) {
        DataResponse<ReturnYNDto> response = new DataResponse<>();
        HeaderDto header        = param.getHeader();
        IDVo listIDDto     = param.getBody();
        TransactionStatus transactionStatus    = transactionManager.getTransaction(new DefaultTransactionDefinition());
        CompanyDto company      = new CompanyDto();
        Long delete = 0l;
        try{
            if (listIDDto.getList().size() > 0) {
                for (IdDto idDto : listIDDto.getList()) {
                    boolean result = false;
                    if (isValidListId(idDto, header.getLanguageCode())) {
                        company.setId(idDto.getId());
                        company.setStatus(Status.Delete.getValueStr());
                        delete = companyService.delete(company);
                    }
                }
            }
            if (delete > 0 ) {
                transactionManager.commit(transactionStatus);
                header.setResult(true);
                response.setHeader(header);
                response.setBody(new ReturnYNDto(true, SYN.Y.getKey()));
              return new ResponseEntity<>(response, HttpStatus.OK);
            }
        } catch (Exception e) {
           transactionManager.rollback(transactionStatus);
            log.error("\n get error api company delete api <<<=== delete() ===>>:\n", e.getMessage());
            throw  e;
        }
        header.setResult(false);
        header.setMsg(msg);
        response.setHeader(header);
        response.setBody(new ReturnYNDto(false, SYN.N.getKey()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * @param idDto,LanguageCode
     * @functionName is isValidListId
     * @description check valid
     **/
    private  boolean isValidListId(IdDto idDto, String LanguageCode) {
        if (idDto.getId() == 0) {
            msg = Translator.toLocale( LanguageCode,"SELECT_ROW_FOR_DELETE");
            return false;
        }
        return  true;
    }

    /**
     * @param companyDto
     * @param LanguageCode
     * valid form control
     **/
    private boolean isValid(CompanyDto companyDto, String LanguageCode) {
        if(companyDto.getName()== null || companyDto.getName().trim().equals("")){
            msg = Translator.toLocale( LanguageCode,"Company_Name_Required");
            return false;
        }
        if(companyDto.getContact() == null || companyDto.getContact().trim().equals("")) {
            msg = Translator.toLocale( LanguageCode,"Contact_Required");
            return false;
        }
        if (companyDto.getEmail() == null || companyDto.getEmail().trim().equals("")) {
            msg = Translator.toLocale( LanguageCode,"Email_Required");
            return false;
        }
        return true;
    }
}
