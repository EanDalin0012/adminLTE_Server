/*
* @Author ean dalin
* @CreateDate 31-03-2020
* @Description Supplier res controller
* */
package com.spring.adminlte.resController.admin;

import com.spring.adminlte.component.Translator;
import com.spring.adminlte.constants.SYN;
import com.spring.adminlte.constants.Status;
import com.spring.adminlte.dto.*;
import com.spring.adminlte.dto.vo.IDVo;
import com.spring.adminlte.templatesDto.DataResponse;
import com.spring.adminlte.templatesDto.RequestData;
import com.spring.adminlte.services.serviceImplements.SupplierServiceImplement;
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
@RequestMapping(value = "/res/supplier")
public class SupplierRestController {
    private static final Logger log = LoggerFactory.getLogger(CompanyRestController.class);
    @Autowired
    private SupplierServiceImplement supplierService;
    @Autowired
    private PlatformTransactionManager transactionManager;

    String msg = "";

    /*
    * @functionName getList
    * @description get list of supplier information
    * @param header
    * */
    @PostMapping(value = "/getList")
    public ResponseEntity<DataResponse<List<SupplierDto>>> getList(@RequestBody RequestData<HeaderDto> header) {
        DataResponse<List<SupplierDto>> response = new DataResponse<>();
        try{
            List<SupplierDto>   list = supplierService.getList(Status.Delete.getValueStr());
            response.setHeader(header.getHeader());
            response.setBody(list);
        }catch (Exception e){
            log.error("\n get error res supplier get list\n", e.getMessage());
            throw  e;
        }
        return  new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*
    * @functionName save
    * @description save information of supplier
    * @param supplier
    * */
    @PostMapping(value = "/save")
    public ResponseEntity<DataResponse<ReturnYNDto>> save(@RequestBody RequestData<SupplierDto> supplier) {
        SupplierDto supplierDto            =  supplier.getBody();
        HeaderDto header                   = supplier.getHeader();
        DataResponse<ReturnYNDto> response = new DataResponse<>();
        try {
                if (supplierDto != null ) {
                    if (isValid(supplierDto, header.getLanguageCode()) == true) {
                        supplierDto.setStatus(Status.Active.getValueStr());
                        Long save = supplierService.save(supplierDto);
                        if (save > 0) {
                            header.setResult(true);
                            response.setHeader(header);
                            response.setBody(new ReturnYNDto(true, SYN.Y.getKey()));
                            return new ResponseEntity<>(response, HttpStatus.OK);
                        }
                    }
                }
        }catch (Exception e) {
            log.error("\n get error res save \n", e.getMessage());
            throw  e;
        }
        // can not save information supplier
        header.setResult(false);
        header.setMsg(msg);
        response.setHeader(header);
        response.setBody(new ReturnYNDto(false, SYN.N.getKey()));
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<DataResponse<ReturnYNDto>> update(@RequestBody DataResponse<SupplierDto> supplier) {
        DataResponse<ReturnYNDto> response = new DataResponse<>();
        HeaderDto header                   = supplier.getHeader();
        SupplierDto supplierDto            = supplier.getBody();
        try {
            if (supplierDto != null) {
                if (supplierDto.getId() == 0) {
                    msg = Translator.toLocale(header.getLanguageCode(), "SUPPLIER_ID_PROBLEM");
                    header.setResult(false);
                    header.setMsg(msg);
                    response.setHeader(header);
                    response.setBody(new ReturnYNDto(false, SYN.N.getKey()));
                } else {
                    if (isValid(supplierDto, header.getLanguageCode()) == true ) {
                        supplierDto.setStatus(Status.Modify.getValueStr());
                        Long update = supplierService.update(supplierDto);
                        if (update > 0) {
                            header.setResult(true);
                            response.setBody(new ReturnYNDto(true, SYN.Y.getKey()));
                            response.setHeader(header);
                            return new ResponseEntity<>(response, HttpStatus.OK);
                        }
                    }
                }
            }
        }catch (Exception e) {
            log.error("\n \n", e.getMessage());
            throw e;
        }
        
        header.setResult(false);
        header.setMsg(msg);
        response.setHeader(header);
        response.setBody(new ReturnYNDto(false, SYN.N.getKey()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*
    * @functionName updateStatus
    * @param param
    * @description update status to delete
    * */
    @PostMapping(value = "/updateStatus")
    public ResponseEntity<RequestData<ReturnYNDto>> updateStatus(@RequestBody RequestData<IDVo> param) {
        RequestData<ReturnYNDto> response = new RequestData<>();
        SupplierDto supplierDto            = new SupplierDto();
        IDVo listIDDto               = param.getBody();
        HeaderDto header                  = param.getHeader();
        TransactionStatus transactionStatus    = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            if (listIDDto.getList().size() > 0) {
                for (IdDto idDto: listIDDto.getList()) {
                    supplierDto.setStatus(Status.Delete.getValueStr());
                    supplierDto.setId(idDto.getId());
                    supplierService.delete(supplierDto);
                }
                transactionManager.commit(transactionStatus);
                header.setResult(true);
                response.setHeader(header);
                response.setBody(new ReturnYNDto(true, SYN.Y.getKey()));
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }catch (Exception e) {
            transactionManager.rollback(transactionStatus);
            log.error("\n \n", e.getMessage());
            throw  e;
        }
        header.setResult(false);
        header.setMsg("Error Exception");
        response.setBody(new ReturnYNDto(false, SYN.N.getKey()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*
    * @functionName isValid
    * @description valid information of supplier
    * @param supplier,languageCode
    * */
    private Boolean isValid(SupplierDto supplier, String languageCode){
        if (supplier.getSupName().trim().equals("") || supplier.getSupName() == null) {
            msg = Translator.toLocale(languageCode, "SUPPLIER_NAME_NULL");
            return  false;
        } else if (supplier.getSupContact().trim().equals("") || supplier.getSupContact() == null) {
            msg = Translator.toLocale(languageCode, "SUPPLIER_CONTACT_NULL");
            return  false;
        } else if (supplier.getSupEmail().trim().equals("") || supplier.getSupEmail() == null) {
            msg = Translator.toLocale(languageCode, "SUPPLIER_EMAIL_NULL");
            return false;
        }
        return true;
    }
}
