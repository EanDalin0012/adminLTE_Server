package com.spring.adminlte.api.restcontroller.admin;
import com.spring.adminlte.component.Translator;
import com.spring.adminlte.constants.SYN;
import com.spring.adminlte.constants.Status;
import com.spring.adminlte.dto.HeaderDto;
import com.spring.adminlte.dto.IdDto;
import com.spring.adminlte.dto.ReturnYNDto;
import com.spring.adminlte.dto.RoleDto;
import com.spring.adminlte.dto.vo.IDVo;
import com.spring.adminlte.dto.vo.RoleVo;
import com.spring.adminlte.services.serviceImplements.RoleServiceImplement;
import com.spring.adminlte.core.template.classes.DataResponse;
import com.spring.adminlte.core.template.classes.RequestData;
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
/*
 * @author Ean Dalin
 * @date 2020.05.18
 * */
@RestController
@RequestMapping(value = "/api/role")
public class RoleRestController {
    private static final Logger log = LoggerFactory.getLogger(RoleRestController.class);

    @Autowired
    private RoleServiceImplement roleService;
    @Autowired
    private PlatformTransactionManager transactionManager;

    String msg = "";

    /*
    * @functionName getList
    * @param param
    * @description retrieve role list
    * */
    @PostMapping(value = "/getList")
    public ResponseEntity<DataResponse<RoleVo>> getList(@RequestBody RequestData<HeaderDto> param) {
        HeaderDto header = param.getHeader();
        DataResponse<RoleVo> response = new DataResponse<>();
        RoleVo list = retrieveRoleList(header);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    /*
     * @functionName save
     * @param param
     * @description save role information
     * */
    @PostMapping(value = "/save")
    public ResponseEntity<DataResponse<ReturnYNDto>> save(@RequestBody RequestData<RoleDto> param) {
        return getResponseDataEntity(param, "save");
    }

    @PostMapping(value = "/update")
    public ResponseEntity<DataResponse<ReturnYNDto>> update(@RequestBody RequestData<RoleDto> param) {
        return getResponseDataEntity(param, "update");
    }

    @PostMapping(value = "/deleteVo")
    public ResponseEntity<DataResponse<ReturnYNDto>> deleteByListId (@RequestBody RequestData<IDVo> param) {
        DataResponse<ReturnYNDto> response = executingUpdateByID(param);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private DataResponse<ReturnYNDto> executingUpdateByID(RequestData<IDVo> param){
        DataResponse<ReturnYNDto> output = new DataResponse<>();
        TransactionStatus transactionStatus    = transactionManager.getTransaction(new DefaultTransactionDefinition());
        RoleDto role = new RoleDto();
        HeaderDto header = new HeaderDto();

        try {
            if (param.getBody().getList().size() > 0) {
                for(IdDto obj: param.getBody().getList()) {
                    role.setId(obj.getId());
                    role.setStatus(Status.Delete.getValueStr());
                    roleService.delete(role);
                }
                transactionManager.commit(transactionStatus);
                header.setResult(true);
                output.setHeader(header);
                output.setBody(new ReturnYNDto(true, SYN.Y.getValue()));
                return output;
            }
        }catch (Exception e) {
            log.error("\n get error executing update status to delete role \n", e.getMessage());
            msg = Translator.toLocale(param.getHeader().getLanguageCode(), "");
            transactionManager.rollback(transactionStatus);
            throw e;

        }

        header.setResult(false);
        header.setMsg(msg);
        output.setHeader(header);
        output.setBody(new ReturnYNDto(false, SYN.N.getValue()));

        return output;
    }

    private ResponseEntity<DataResponse<ReturnYNDto>> getResponseDataEntity(RequestData<RoleDto> param, String note) {
        DataResponse<ReturnYNDto> response = executing(param, note);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*
    *@functionName executing
    *@param para note
    *@description save or update role information
    * */
    private DataResponse<ReturnYNDto> executing(RequestData<RoleDto> param, String note)  {
        DataResponse<ReturnYNDto> output = new DataResponse<>();
        HeaderDto header = new HeaderDto();
        int save = 0;
        try {
            if (isValid(param.getHeader().getLanguageCode(), param.getBody()) == true) {
                switch (note) {
                    case "save":
                        save = roleService.save(param.getBody());
                        break;
                    case "update":
                        if (param.getBody().getId() != 0) {
                            save = roleService.update(param.getBody());
                        } else {
                            msg = Translator.toLocale(param.getHeader().getLanguageCode(), "Role_ID_Undefined");
                        }
                        break;
                }

                if (save > 0) {
                    header.setResult(true);
                    output.setHeader(header);
                    output.setBody(new ReturnYNDto(true, SYN.Y.getKey()));
                    return output;
                }
            }
        } catch (Exception e) {
            log.error("\n get error save role\n",e.getMessage());
            throw  e;
        }

        header.setResult(false);
        header.setMsg(msg);
        output.setBody(new ReturnYNDto(false, SYN.N.getKey()));
        output.setHeader(header);

        return output;
    };

    private RoleVo retrieveRoleList(HeaderDto param) {
        RoleVo response = new RoleVo();
        try{
            List<RoleDto> list = roleService.getList(Status.Delete.getValueStr());
            response.setList(list);
        }catch (Exception e) {
            log.error("\n get error retrieve role list\n", e.getMessage());
            throw  e;
        }
        return response;
    }

    private boolean isValid(String langCode, RoleDto role) {
        if (role.getRoleName().equals("") || role.getRoleName() == null ) {
            msg = Translator.toLocale(langCode, "Role_Name_Require");
            return false;
        }
        return true;
    }

}
