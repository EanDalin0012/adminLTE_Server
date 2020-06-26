package com.spring.adminlte.api.restcontroller.admin;
import com.spring.adminlte.constants.Status;
import com.spring.adminlte.core.map.MMap;
import com.spring.adminlte.core.map.MultiMap;
import com.spring.adminlte.core.template.classes.ResponseData;
import com.spring.adminlte.services.serviceImplements.RoleServiceImplement;
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

/**
 * <pre>
 *     api for role
 * </pre>
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


    /**
     * <pre>
     *     get list of role
     * </pre>
    * @param param
    * @return  retrieve role list
    * */
    @PostMapping(value = "/getList")
    public ResponseEntity<ResponseData<MMap, MMap>> getList(@RequestBody MMap param) {
        ResponseData<MMap, MMap> response = new ResponseData<>();
        MMap header = param.getMMap("header");

        try{
            MMap input = new MMap();
            MMap output = new MMap();

            input.setString("status", Status.Delete.getValueStr());
            MultiMap list = roleService.getList(input);
            output.setMultiMap("list", list);

            response.setHeader(header);
            response.setBody(output);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e) {
            log.error("\n get error get list of api role",e);
            throw e;
        }
    }

    /**
     * <pre>
     *     save information of role
     * </pre>
     * @param param
     * @return  ResponseData<MMap, MMap>
     * */
    @PostMapping(value = "/save")
    public ResponseEntity<ResponseData<MMap, MMap>> save(@RequestBody MMap param) throws Exception {
        return execute(param, "save");
    }

    @PostMapping(value = "/update")
    public ResponseEntity<ResponseData<MMap, MMap>> update(@RequestBody MMap param) throws Exception {
        return execute(param, "update");
    }

    @PostMapping(value = "/deleteVo")
    public ResponseEntity<ResponseData<MMap, MMap>> deleteByListId (@RequestBody MMap param) throws Exception {
        ResponseData<MMap, MMap> response = new ResponseData<>();
        MMap header                       = param.getMMap("header");
        MMap body                         = param.getMMap("body");
        MultiMap list                     = body.getMultiMap("list");

        TransactionStatus transactionStatus    = transactionManager.getTransaction(new DefaultTransactionDefinition());
        try{
            int count = list.size();
            for (int i =0; i < count; i++) {
                MMap input = list.getData(i);
                input.setString("status", Status.Delete.getValueStr());
                System.out.println(input);
                roleService.delete(input);
            }

            MMap resBody  = new MMap();
            resBody.setString("returnYN", "Y");
            response.setHeader(header);
            response.setBody(resBody);
            transactionManager.commit(transactionStatus);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e) {
            log.error("\n get error api sub category save error:\n" + e.getMessage());
            transactionManager.rollback(transactionStatus);
            throw e;
        }
    }

    /**
     * <pre>
     *     register or update information of role
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

            ValidatorUtil.validate(body, "proName", "subCateId", "resourceFileInfoId");

            input.setString("roleName",             body.getString("proName"             ));
            input.setString("description",          body.getString("description"         ));

            if (function == "save") {
                input.setString("status",   Status.Active.getValueStr());
                int save = roleService.save(input);
                if (save > 0 ) {
                    Yn = "Y";
                }
            }
            if (function == "update") {
                input.setLong("id"  ,     body.getLong("id")  );
                input.setString("status", Status.Modify.getValueStr() );
                int update = roleService.update(input);
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
