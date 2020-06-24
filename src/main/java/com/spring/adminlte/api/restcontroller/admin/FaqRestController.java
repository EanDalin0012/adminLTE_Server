package com.spring.adminlte.api.restcontroller.admin;

import com.spring.adminlte.constants.Status;
import com.spring.adminlte.core.map.MMap;
import com.spring.adminlte.core.map.MultiMap;
import com.spring.adminlte.core.template.classes.ResponseData;
import com.spring.adminlte.services.serviceImplements.FaqServiceImplement;
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
@RequestMapping(value = "/api/faq")
public class FaqRestController {

    private static final Logger log = LoggerFactory.getLogger(FaqRestController.class);

    @Autowired
    private FaqServiceImplement faqService;
    private PlatformTransactionManager transactionManager;

    @PostMapping(value = "/getList")
    public ResponseEntity<ResponseData<MMap, MMap>> getList(@RequestBody MMap param) {
        ResponseData<MMap, MMap> response = new ResponseData<>();
        try {
            MMap input  = new MMap();
            MMap output = new MMap();
            MMap header = param.getMMap("header");

            input.setString("status", Status.Delete.getValueStr());
            MultiMap list = faqService.getList(input);

            output.setMultiMap("list", list);
            response.setHeader(header);
            response.setBody(output);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e) {
            log.error("get error exception of api faq get list", e);
            throw e;
        }
    }

    /**
     * <pre>
     *     save information of faq
     * </pre>
     * @param  param
     * @return ResponseData<MMap, MMap>
     * @throws Exception
     * */
    @PostMapping(value = "/save")
    public ResponseEntity<ResponseData<MMap, MMap>> save (@RequestBody MMap param) throws Exception {
        return execute(param,"save");
    }

    /**
     * <pre>
     *     save information of faq
     * </pre>
     * @param  param
     * @return ResponseData<MMap, MMap>
     * @throws Exception
     * */
    @PostMapping(value = "/update")
    public ResponseEntity<ResponseData<MMap, MMap>> update (@RequestBody MMap param) throws Exception {
        return execute(param,"update");
    }


    /**
     * <pre>
     *     register or update information of FAQ
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

            ValidatorUtil.validate(body, "EnTitle", "KHRTitle", "CHTitle");

            input.setString("enTitle",         body.getString("EnTitle"     ));
            input.setLong("khTitle",           body.getLong("KHRTitle"      ));
            input.setString("chTitle",         body.getString("CHTitle"     ));
            input.setLong("userID",            getHeader.getLong("userID"   ));
            input.setString("enText",          body.getString("EnText"      ));
            input.setString("khrText",         body.getString("KHRText"     ));
            input.setString("chText",          body.getString("CHText"      ));

            if (function == "save") {
                input.setString("status",   Status.Active.getValueStr());
                int save = faqService.save(input);
                if (save > 0 ) {
                    Yn = "Y";
                }
            }
            if (function == "update") {
                input.setLong("id"  ,     body.getLong("id")  );
                input.setString("status", Status.Modify.getValueStr() );
                int update = faqService.update(input);
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
