/*
* @Author ean dalin
* @CreateDate 31-03-2020
* @Description Supplier res controller
* */
package com.spring.adminlte.api.restcontroller.admin;
import com.spring.adminlte.constants.Status;
import com.spring.adminlte.core.map.MMap;
import com.spring.adminlte.core.map.MultiMap;
import com.spring.adminlte.services.serviceImplements.SupplierServiceImplement;
import com.spring.adminlte.core.template.classes.ResponseData;
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
import java.util.List;

@RestController
@RequestMapping(value = "/res/supplier")
public class SupplierRestController {
    private static final Logger log = LoggerFactory.getLogger(CompanyRestController.class);
    @Autowired
    private SupplierServiceImplement supplierService;
    @Autowired
    private PlatformTransactionManager transactionManager;


    /**
     * <pre>
     *     get list of supplier
     * </pre>
    * @param param
    * @return  ResponseData<MMap,List<MMap>>
    * @throws  Exception
    * */
    @PostMapping(value = "/getList")
    public ResponseEntity<ResponseData<MMap,List<MMap>>> getList(@RequestBody MMap param) {
        MMap header = param.getMMap("header");
        try{
            ResponseData<MMap,List<MMap>> responseData = new ResponseData<>();
            MMap input = new MMap();
            input.setString("status", Status.Delete.getValueStr());
            List<MMap>   list = supplierService.getList(input);
            responseData.setHeader(header);
            responseData.setBody(list);
            return  new ResponseEntity<>(responseData, HttpStatus.OK);
        }catch (Exception e){
            log.error("\n get error res supplier get list\n", e.getMessage());
            throw  e;
        }
    }

    /**
     * <pre>
     *     save information of supplying
     * </pre>
    * @param param
     * @return ResponseData<MMap, MMap>
     * @throws Exception
    * */
    @PostMapping(value = "/save")
    public ResponseEntity<ResponseData<MMap, MMap>> save(@RequestBody MMap param) throws Exception {
        return  responseEntityInfo(param, "save");
    }

    /**
     * <pre>
     *     update information of supplying
     * </pre>
     * @param param
     * @return ResponseData<MMap,MMap>
     * @throws Exception
    * */
    @PostMapping(value = "/update")
    public ResponseEntity<ResponseData<MMap,MMap>> update(@RequestBody MMap param) throws Exception {
        return responseEntityInfo(param, "update");
    }

    /*
    * @functionName updateStatus
    * @param param
    * @description update status to delete
    * */
    @PostMapping(value = "/updateStatus")
    public ResponseEntity<ResponseData<MMap, MMap>> updateStatus(@RequestBody MMap param) {
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
                supplierService.delete(input);
            }
            MMap resBody  = new MMap();
            resBody.setString("returnYN", "Y");

            response.setHeader(header);
            response.setBody(resBody);
            transactionManager.commit(transactionStatus);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e) {
            log.error("\n get error category get list by Id ===>>>:", e.getMessage());
            transactionManager.rollback(transactionStatus);
            throw  e;
        }
    }

    /**
     * <pre>
     *     register or update information of main category
     * </pre>
     * @param param
     * @param  function
     * @return ResponseEntity<MMap>
     * @throws Exception
     * */
    private ResponseEntity<ResponseData<MMap, MMap>> responseEntityInfo(MMap param, String function) throws Exception {
        ResponseData<MMap, MMap> response = new ResponseData<>();
        MMap getHeader     = param.getMMap("header");
        MMap getBody       = param.getMMap("body");
        TransactionStatus transactionStatus    = transactionManager.getTransaction(new DefaultTransactionDefinition());

        try {
            MMap input          = new MMap();
            MMap responseBody   = new MMap();
            String Yn           = "N";

            ValidatorUtil.validate(getBody, "supName", "supContact", "supEmail");

            input.setString("supName",      getBody.getString("supName"     ));
            input.setString("supContact",   getBody.getString("supContact"  ));
            input.setString("supContactTwo",getBody.getString("supContactTwo"));
            input.setString("supEmail",     getBody.getString("supEmail"    ));
            input.setString("description",  getBody.getString("description" ));
            input.setLong("userID",         getHeader.getLong("userID"      ));

            if (function == "save") {
                input.setString("status",   Status.Active.getValueStr());
                Long save = supplierService.save(input);
                if (save > 0 ) {
                    Yn = "Y";
                }
            }
            if (function == "update") {
                input.setLong("id"  ,     getBody.getLong("id")  );
                input.setString("status", Status.Modify.getValueStr());
                Long update = supplierService.update(input);
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
