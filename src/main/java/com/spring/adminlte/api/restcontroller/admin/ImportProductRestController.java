/*
* @author Ean dlin
* @description import product api
* @date 18-04-2020
* */
package com.spring.adminlte.api.restcontroller.admin;
import com.spring.adminlte.constants.Status;
import com.spring.adminlte.core.map.MMap;
import com.spring.adminlte.core.map.MultiMap;
import com.spring.adminlte.core.template.classes.ResponseData;
import com.spring.adminlte.dao.ImportProductDetailsDao;
import com.spring.adminlte.services.serviceImplements.ImportProductServiceImplement;
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

@RestController
@RequestMapping(value = "/api/import-product")
public class ImportProductRestController {
    private static final Logger log = LoggerFactory.getLogger(ImportProductRestController.class);

    @Autowired
    private ImportProductServiceImplement importProductService;
    @Autowired
    private ImportProductDetailsDao importProductDetailsDao;
    @Autowired
    private PlatformTransactionManager transactionManager;


    /**
     * <pre>
     *     get list of import product
     * </pre>
    * @param param
     * @return
     * @throws
    * */
    @PostMapping(value = "/getListDetails")
    public ResponseEntity<ResponseData<MMap, MMap>> inquiryImportProductDetails(@RequestBody MMap param) {
        ResponseData<MMap, MMap> response = new ResponseData<>();
        MMap header = param.getMMap("header");

        try{
            MMap input = new MMap();
            MMap output = new MMap();

            input.setString("status", Status.Delete.getValueStr());
            MultiMap list = importProductDetailsDao.getList(input);
            output.setMultiMap("list", list);

            response.setBody(output);
            response.setHeader(header);
            return new ResponseEntity<>(response, HttpStatus.OK);

        }catch (Exception e) {
            log.error("\n ====> get error inquiry import product details <=== \n");
            throw  e;
        }
    }

    /**
     * <pre>
     *     get list import product
     * </pre>
    * @param param
     * @return
     * @throws
    * */
    @PostMapping(value = "/getList")
    public ResponseEntity<ResponseData<MMap, MMap>> getList(@RequestBody MMap param) {
        ResponseData<MMap, MMap> response = new ResponseData<>();
        MMap header = param.getMMap("header");

        try{
            MMap input      = new MMap();
            MMap output     = new MMap();

            input.setString("status", Status.Delete.getValueStr());
            MultiMap list   = importProductService.getList(input);
            output.setMultiMap("list", list);

            response.setHeader(header);
            response.setBody(output);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e) {
            log.error("\n ===>> get error get list of import product <===\n", e.getMessage());
            throw e;
        }

    }
    /**
     * <pre>
     *     register of import product
     * </pre>
    * @param param
     * @return ResponseData<MMap, MMap>
     * @throws Exception
    * */
    @PostMapping(value = "/save")
    public ResponseEntity<ResponseData<MMap, MMap>> save (@RequestBody MMap param) throws Exception {
        return execute(param, "save");
    }

    /**
     * <pre>
     *     update information of import product
     * </pre>
     * @param param
     * @return ResponseData<MMap, MMap>
     * @throws Exception
     * */
    @PostMapping(value = "/update")
    public ResponseEntity<ResponseData<MMap, MMap>> update (@RequestBody MMap param) throws Exception {
        return execute(param, "save");
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
    private ResponseEntity<ResponseData<MMap, MMap>> execute(MMap param, String function) throws Exception {
        ResponseData<MMap, MMap> response = new ResponseData<>();
        MMap getHeader  = param.getMMap("header");
        MMap body       = param.getMMap("body");
        TransactionStatus transactionStatus    = transactionManager.getTransaction(new DefaultTransactionDefinition());

        try {
            MMap input          = new MMap();
            MMap responseBody   = new MMap();
            String Yn           = "N";

            ValidatorUtil.validate(param, "productId", "supplierId", "companyId","quantity","price","total","discount", "currencyCode");

            input.setString("productId",    body.getString("productId"      ));
            input.setLong("supplierId",     body.getLong("companyId"        ));
            input.setLong("companyId",      body.getLong("companyId"        ));
            input.setLong("quantity",       body.getLong("quantity"         ));
            input.setLong("price",          body.getLong("price"            ));
            input.setString("total",        body.getString("total"          ));
            input.setString("discount",     body.getString("discount"       ));
            input.setString("currencyCode", body.getString("currencyCode"   ));
            input.setLong("userID",         getHeader.getLong("userID"      ));
            input.setString("description",  body.getString("description"    ));

            if (function == "save") {
                input.setString("status",   Status.Active.getValueStr());
                Long save = importProductService.save(input);
                if (save > 0 ) {
                    Yn = "Y";
                }
            }
            if (function == "update") {
                input.setLong("id"  ,     body.getLong("id")  );
                input.setString("status", Status.Modify.getValueStr() );
                Long update = importProductService.update(input);
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
