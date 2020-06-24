package com.spring.adminlte.api.restcontroller.admin;

import com.spring.adminlte.constants.Status;
import com.spring.adminlte.core.map.MMap;
import com.spring.adminlte.core.map.MultiMap;
import com.spring.adminlte.core.template.classes.ResponseData;
import com.spring.adminlte.services.serviceImplements.UserServiceImplement;
import com.spring.adminlte.core.template.classes.DataResponse;
import com.spring.adminlte.utils.ValidatorUtil;
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
    @Autowired
    private PlatformTransactionManager transactionManager;

    String msg = "";

    /* @function getList
    * @param param
    * @description get user information list
    * */
    @PostMapping(value = "/list")
    public ResponseEntity<ResponseData<MMap, MMap>> getList(@RequestBody MMap param) throws Exception{
        ResponseData<MMap, MMap> response = new ResponseData<>();
        try {
            MMap input  = new MMap();
            MMap out    = new MMap();
            MMap header = param.getMMap("header");

            input.setString("status", Status.Delete.getValueStr());
            MultiMap list = userService.getList(input);
            out.setMultiMap("list", list);
            response.setHeader(header);
            response.setBody(out);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("get error exception of get user list", e);
            throw e;
        }
    }

    /* @function save
     * @param param
     * @description save information of user
     * */
    @PostMapping(value = "/save")
    public ResponseEntity<ResponseData<MMap, MMap>> save(@RequestBody MMap param) throws Exception{
        return execute(param, "save");
    }

    /* @function update
     * @param param
     * @description save information of user
     * */
    @PostMapping(value = "/update")
    public ResponseEntity<ResponseData<MMap, MMap>> update(@RequestBody MMap param) throws Exception{
        return execute(param, "save");
    }

    /**
     * <pre>
     *     register or update information of user
     * </pre>
     * @param param
     * @param  function
     * @return ResponseEntity<MMap>
     * @throws Exception
     * */
    private ResponseEntity<ResponseData<MMap, MMap>> execute(MMap param, String function) throws Exception {
        ResponseData<MMap, MMap> response = new ResponseData<>();
        MMap getHeader  = param.getMMap("header");
        MMap body       = param.getMMap("body");
        TransactionStatus transactionStatus    = transactionManager.getTransaction(new DefaultTransactionDefinition());

        try {
            MMap input          = new MMap();
            MMap responseBody   = new MMap();
            String Yn           = "N";

            ValidatorUtil.validate(body, "firstName", "lastName", "email", "password", "gender", "contact", "dateOfBirth", "address");

            input.setString("proName",              body.getString("proName"             ));
            input.setLong("subCateId",              body.getLong("subCateId"             ));
            input.setString("resourceFileInfoId",   body.getString("resourceFileInfoId"  ));
            input.setLong("userID",                 getHeader.getLong("userID"           ));
            input.setString("description",          body.getString("description"         ));

            if (function == "save") {
                input.setString("status",   Status.Active.getValueStr());
                int save = userService.save(input);
                if (save > 0 ) {
                    Yn = "Y";
                }
            }
            if (function == "update") {
                input.setLong("id"  ,     body.getLong("proId")  );
                input.setString("status", Status.Modify.getValueStr() );
                int update = userService.update(input);
                if (update > 0 ) {
                    Yn = "Y";
                }
            }

            transactionManager.commit(transactionStatus);
            responseBody.setString("returnYN", Yn);
            response.setHeader(getHeader);
            response.setBody(responseBody);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            transactionManager.rollback(transactionStatus);
            log.error("get Exception ", e);
            throw e;
        }
    }
}
