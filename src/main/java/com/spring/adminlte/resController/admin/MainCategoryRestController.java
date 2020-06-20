/**
 * <pre>
 *     Main Category API (Application Program Interface)
 * </pre>
 * @CreateDate 02/03/2020
 * @Author Ean Dalin
 * @API api/main_category
 **/
package com.spring.adminlte.resController.admin;

import com.spring.adminlte.constants.Status;
import com.spring.adminlte.dao.SequenceDao;
import com.spring.adminlte.mmap.MMap;
import com.spring.adminlte.mmap.MultiMap;
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
public class MainCategoryRestController {
    private static final Logger log = LoggerFactory.getLogger(CompanyRestController.class);

    @Autowired
    private MainCategoryServiceImplement mainCategoryService;
    @Autowired
    private PlatformTransactionManager transactionManager;
    @Autowired
    private SequenceDao sequenceDao;
//    @Autowired
//    private MainCategoryDao mainCategoryDao;
    String msg = "";

    /**
     * <pre>
     *     retrieve all list of main category <br/> not status delete
     * </pre>
     * @param param
     * @functionName getList
     * @description get main category list
     **/
    @PostMapping(value = "/getList")
    public ResponseEntity<ResponseData<MMap, List<MMap>>> getList(@RequestBody MMap param) {
        ResponseData<MMap, List<MMap>> dataResponse = new ResponseData<>();

        try {
            MMap headerDto      = param.getMMap("header");
            List<MMap> response = mainCategoryService.getList(Status.Active.getValueStr());
            dataResponse.setBody(response);
            dataResponse.setHeader(headerDto);
            return new ResponseEntity<>(dataResponse, HttpStatus.OK);
        }catch (Exception e) {
            log.error("\nget Error api main category list <<<=== getList() ===>>>:\n", e.getMessage());
            msg = "GET_ERROR_EXCEPTION";
           throw e;
        }
    }

    /**
     * <pre>
     *     register information of main category
     * </pre>
     * @param param
     * @functionaName save
     * @description save main category information
     **/
    @PostMapping(value = "/save")
    public ResponseEntity<ResponseData<MMap,MMap >> save(@RequestBody MMap param) throws Exception {
        return responseEntityInfo(param, "save");
    }

    /**
     * <pre>
     *     update information of main category
     * </pre>
     * @param param
     * @functionName Update
     * @description update Main Category
     **/
    @PostMapping(value = "/update")
    public ResponseEntity<ResponseData<MMap, MMap>> update(@RequestBody MMap param) throws Exception {
        return responseEntityInfo(param, "update");
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
        ResponseData<MMap, MMap> response      = new ResponseData<>();
        MMap getHeader     = param.getMMap("header");
        MMap getBody       = param.getMMap("body");

        try {
            MMap input     = new MMap();
            MMap responseBody  = new MMap();
            String Yn       = "N";

            input.setString("mainCategoryName"  , getBody.getString("mainCategoryName"    ));
            input.setString("description"       , getBody.getString("description"         ));
            input.setLong("userID"              , getHeader.getLong("userID"              ));

            if (function == "save") {
                int sequenceNo  = sequenceDao.getSequenceMainCategory();
                input.setLong("id"  ,       sequenceNo);
                input.setString("status",   Status.Active.getValueStr());
                Long save = mainCategoryService.save(input);
                if (save > 0) {
                    Yn = "Y";
                }
            }
            if (function == "update") {
                input.setLong("id"  , getBody.getLong("id")  );
                input.setString("status"            , Status.Modify.getValueStr());
                Long update = mainCategoryService.update(input);
                if (update > 0) {
                    Yn = "Y";
                }
            }

            responseBody.setString("returnYN", Yn);
            response.setHeader(getHeader);
            response.setBody(responseBody);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("get Exception ", e);
            System.out.println(e.getMessage());
            throw new Exception(e);
        }
    }

    /**
     * <pre>
     *     update status to delete main category information
     * </pre>
     * @param param
     * @return  ResponseData<MMap, MMap>
     * @throws  Exception
     **/
    @PostMapping(value = "/deleteByListId")
    public ResponseEntity<ResponseData<MMap, MMap>> deleteByIDList(@RequestBody MMap param) throws Exception {
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
                mainCategoryService.delete(input);
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
     *     get value of main category by id
     * </pre>
    * */
    @PostMapping(value = "/id")
    public ResponseEntity<ResponseData<MMap, MMap>> getValueById(@RequestBody MMap param) throws Exception {
        ResponseData<MMap, MMap> responseData = new ResponseData<>();
        MMap header = param.getMMap("header");
        MMap body   = param.getMMap("body");
        try {
            MMap input = new MMap();
            input.setLong("id", body.getLong("id"));

            MMap response = mainCategoryService.getValueById(input);

            responseData.setHeader(header);
            responseData.setBody(response);

        }catch (Exception e) {
            log.error("get error exception of getValueById", e);
            throw e;
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
