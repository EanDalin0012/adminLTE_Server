package com.spring.adminlte.api.restcontroller.admin;

import com.spring.adminlte.component.Translator;
import com.spring.adminlte.constants.SYN;
import com.spring.adminlte.constants.Status;
import com.spring.adminlte.dto.HeaderDto;
import com.spring.adminlte.dto.ReturnYNDto;
import com.spring.adminlte.dto.UserDto;
import com.spring.adminlte.dto.vo.UserVo;
import com.spring.adminlte.services.serviceImplements.UserServiceImplement;
import com.spring.adminlte.core.template.classes.DataResponse;
import com.spring.adminlte.core.template.classes.RequestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/*
* @author ean dalin
* @date 23/05/2020
* */
@RestController
@RequestMapping(value = "/api/user")
public class UserRestController {
    private static final Logger log = LoggerFactory.getLogger(UserRestController.class);

    @Autowired
    private UserServiceImplement userService;
    String msg = "";

    /* @function getList
    * @param param
    * @description get user information list
    * */
    @PostMapping(value = "/list")
    public ResponseEntity<DataResponse<UserVo>> getList(@RequestBody HeaderDto param) throws Exception{
        DataResponse<UserVo> response = new DataResponse<>();
        HeaderDto header = param;
        try {
            UserVo list = userVo();
            header.setResult(true);
            response.setHeader(header);
            response.setBody(list);
        }catch (Exception e) {
            log.error("\n get error service get list of user\n", e.getMessage());
            throw e;
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /* @function save
     * @param param
     * @description save information of user
     * */
    @PostMapping(value = "/save")
    public ResponseEntity<DataResponse<ReturnYNDto>> save(RequestData<UserDto> param) {
        return getResponseDataEntity(param, "save");
    }

    /* @function update
     * @param param
     * @description save information of user
     * */
    @PostMapping(value = "/update")
    public ResponseEntity<DataResponse<ReturnYNDto>> update(@RequestBody RequestData<UserDto> param) {
        return getResponseDataEntity(param, "update");
    }

    public ResponseEntity<DataResponse<UserDto>> getValueByID() {
        DataResponse<UserDto> dataResponse = new DataResponse<>();

        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }

    private ResponseEntity<DataResponse<ReturnYNDto>> getResponseDataEntity(RequestData<UserDto> param, String note) {
        DataResponse<ReturnYNDto> response = new DataResponse<>();
        HeaderDto header = param.getHeader();
        UserDto user = param.getBody();
        try {
            ReturnYNDto returnYN = execute(user, header.getLanguageCode(), note);
            header.setResult(false);
            if (returnYN.isResult() == true) {
                header.setResult(true);
            }
            response.setHeader(header);
            response.setBody(returnYN);
        } catch (Exception e) {
            log.error("\n get error save service of user\n", e.getMessage());
            throw e;
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private UserVo userVo() {
        UserVo output = new UserVo();
        List<UserDto> list = userService.getList(Status.Delete.getValueStr());
        output.setList(list);
        return output;
    }

    private ReturnYNDto execute(UserDto input, String langCode, String note) {
        ReturnYNDto output = new ReturnYNDto(false, SYN.N.getValue());

        int save = 0;
        switch (note) {
            case "save":
                if (!isValid(input, langCode)) {
                    save = userService.save(input);
                }
            case "update":
                if (input.getId() == 0) {
                    msg = Translator.toLocale(langCode, "User_ID_Require");
                } else {
                    save = userService.update(input);
                }
                break;
        }
        if (save > 0) {
            output.setResult(true);
            output.setResultMessage(SYN.Y.getValue());
        }
        return output;
    }

    private boolean isValid(UserDto input, String langCode) {
        if (input.getFirstName().equals("") || input.getFirstName().isEmpty() || input.getFirstName() == null) {
            msg = Translator.toLocale(langCode, "User_First_Name_Require");
            return false;
        }
        if (input.getLastName().equals("") || input.getLastName().isEmpty() || input.getLastName() == null) {
            msg = Translator.toLocale(langCode, "User_Last_Name_Require");
            return false;
        }
        if (input.getEmail().equals("") || input.getEmail().isEmpty() || input.getEmail() == null) {
            msg = Translator.toLocale(langCode, "User_Email_Require");
            return false;
        }
        if (input.getContact().equals("") || input.getContact().isEmpty() || input.getContact() == null) {
            msg = Translator.toLocale(langCode, "User_Contact_Require");
            return false;
        }
        if (input.getGender().equals("") || input.getGender().isEmpty() || input.getGender() == null) {
            msg = Translator.toLocale(langCode, "User_Gender_Require");
            return false;
        }
        if (input.getDateOfBirth().equals("") || input.getDateOfBirth().isEmpty() || input.getDateOfBirth() == null) {
            msg = Translator.toLocale(langCode, "User_DOB_Require");
            return false;
        }
        if (input.getPassword().equals("") || input.getPassword().isEmpty() || input.getPassword() == null) {
            msg = Translator.toLocale(langCode, "User_Password_Require");
            return false;
        }
        if (input.getAddress().equals("") || input.getAddress().isEmpty() || input.getAddress() == null) {
            msg = Translator.toLocale(langCode, "User_Address_Require");
            return false;
        }
        return true;
    }
}
