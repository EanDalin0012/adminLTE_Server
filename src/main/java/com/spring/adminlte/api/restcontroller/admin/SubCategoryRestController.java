/**
 * @CreateDate 08/03/2020
 * @Author Ean Dalin
 * @API sub category
 **/
package com.spring.adminlte.api.restcontroller.admin;

import com.spring.adminlte.constants.Status;
import com.spring.adminlte.core.template.classes.ResponseData;
import com.spring.adminlte.dao.SequenceDao;
import com.spring.adminlte.dao.SubCateDetailsDao;
import com.spring.adminlte.dao.SubCategoryDao;
import com.spring.adminlte.core.map.MMap;
import com.spring.adminlte.core.map.MultiMap;
import com.spring.adminlte.services.serviceImplements.SubCategoryServiceImplement;
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
@RequestMapping(value = "api/sub_category")
public class SubCategoryRestController {
    private static final Logger log = LoggerFactory.getLogger(CompanyRestController.class);
    @Autowired
    private SubCategoryServiceImplement subCategoryService;

    @Autowired
    private PlatformTransactionManager transactionManager;
    @Autowired
    private SequenceDao sequenceDao;
    @Autowired
    private SubCategoryDao subCategoryDao;
    @Autowired
    private SubCateDetailsDao subCateDetailsDao;

    /**
     * <pre>
     *     get sub category list
     * </pre>
     * @functionName getList
     * @param  param
     * @return list of sub category
     **/
    @PostMapping(value = "/getList")
    public ResponseEntity<ResponseData<MMap,List<MMap>>> getList(@RequestBody MMap param) {
        ResponseData<MMap,List<MMap>> responseData = new ResponseData<>();
        MMap header = param.getMMap("header");
        try{
            MMap input = new MMap();
            input.setString("status", Status.Delete.getValueStr());
            List<MMap> list = subCateDetailsDao.retrieveSubCategoryDetails(input);
            responseData.setHeader(header);
            responseData.setBody(list);
            return new ResponseEntity<>(responseData, HttpStatus.OK);
        }catch (Exception e) {
            log.error("\n get error api sub category get list:\n"+e.getMessage());
            throw e;
        }

    }


    /**
     * <pre>
     *     save information of sub category
     * </pre>
     * @param  param
     * @return ResponseEntity<ResponseData<MMap, MMap>>
     * @throws Exception
     **/
    @PostMapping(value = "/save")
    public ResponseEntity<ResponseData<MMap, MMap>> save (@RequestBody MMap param) throws Exception {
        return responseEntityInfo(param, "save");
    }

    /**
     * <pre>
     *     update information of sub category
     * </pre>
     * @param  param
     * @return ResponseEntity<ResponseData<MMap,MMap >>
     * @throws Exception
     **/
    @PostMapping(value = "/update")
    public ResponseEntity<ResponseData<MMap,MMap >> update (@RequestBody MMap param) throws Exception {
        return responseEntityInfo(param, "update");
    }

    /**
     * @functionName deleteByListId
     * @param  param
     * @description get delete sub category by list of iD
     * @return response
     **/
    @PostMapping(value = "/deleteByListID")
    public ResponseEntity< ResponseData<MMap, MMap>> deleteByListId (@RequestBody MMap param) throws Exception {
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
                subCategoryService.delete(input);
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
     *     get sub category information
     * </pre>
     * @param  param
     * @return ResponseEntity<ResponseData<MMap, MMap>>
     * @throws Exception
     **/
    @PostMapping(value = "/id")
    public ResponseEntity<ResponseData<MMap, MMap>> getValueById(@RequestBody MMap param) {
        ResponseData<MMap, MMap> responseData = new ResponseData<>();
        MMap header = param.getMMap("header");
        MMap body   = param.getMMap("body");
        try {
            MMap input = new MMap();
            input.setLong("id", body.getLong("id"));
            MMap response = subCategoryService.getValueById(input);
            responseData.setHeader(header);
            responseData.setBody(response);
        }catch (Exception e) {
            log.error("get error Exception getValueById", e);
            throw e;
        }
        return new ResponseEntity<>(responseData, HttpStatus.OK);
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

            ValidatorUtil.validate(getBody, "subCategoryName", "mainCategoryId");

            input.setString("subCategoryName",  getBody.getString("subCategoryName"));
            input.setString("description",      getBody.getString("description"    ));
            input.setLong("userID",     getHeader.getLong("userID"));

            if (function == "save") {
                int sequenceNo  = sequenceDao.getSequenceSubCategory();
                input.setLong("id"  ,       sequenceNo);
                input.setString("status",   Status.Active.getValueStr());


                MMap inputDetail    = new MMap();
                inputDetail.setLong("mnCateId", getBody.getLong("mainCategoryId"));
                inputDetail.setLong("sbCateId", sequenceNo);

                Long save = subCategoryService.save(input);
                Long saveDetail = subCategoryDao.saveDetail(inputDetail);
                if (save > 0 && saveDetail > 0) {
                    Yn = "Y";
                }
            }
            if (function == "update") {
                input.setLong("id"  ,     getBody.getLong("id")  );
                input.setString("status", Status.Modify.getValueStr());

                MMap inputDetail    = new MMap();
                inputDetail.setLong("mnCateId", getBody.getLong("mainCategoryId"));
                inputDetail.setLong("sbCateId", getBody.getLong("id"));

                Long update = subCategoryService.update(input);
                Long updateDetail = subCategoryDao.updateDetail(inputDetail);
                if (update > 0 && updateDetail > 0) {
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
