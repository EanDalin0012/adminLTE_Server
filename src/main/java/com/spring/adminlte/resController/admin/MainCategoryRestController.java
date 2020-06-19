/**
 * <pre>
 *     Main Category API (Application Program Interface)
 * </pre>
 * @CreateDate 02/03/2020
 * @Author Ean Dalin
 * @API api/main_category
 **/
package com.spring.adminlte.resController.admin;

import com.spring.adminlte.component.Translator;
import com.spring.adminlte.constants.SYN;
import com.spring.adminlte.constants.Status;
import com.spring.adminlte.dao.MainCategoryDao;
import com.spring.adminlte.dao.SequenceDao;
import com.spring.adminlte.dto.*;
import com.spring.adminlte.dto.vo.IDVo;
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

import java.util.ArrayList;
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
    @Autowired
    private MainCategoryDao mainCategoryDao;
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
    public ResponseEntity<MMap> save(@RequestBody MMap param) throws Exception {
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
    public ResponseEntity<MMap> update(@RequestBody MMap param) throws Exception {
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
    private ResponseEntity<MMap> responseEntityInfo(MMap param, String function) throws Exception {
        MMap response      = new MMap();
        MMap getHeader     = param.getMMap("header");
        MMap getBody       = param.getMMap("body");

        try {
            MMap input     = new MMap();
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
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
            }
            if (function == "update") {
                input.setLong("id"  , getBody.getLong("id")  );
                input.setString("status"            , Status.Modify.getValueStr());

                Long update = mainCategoryService.update(input);

                if (update > 0) {
                    Yn = "Y";
                    response.setString("returnYN", Yn);
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
            }

            response.setString("returnYN", Yn);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            log.error("get Exception ", e);
            System.out.println(e.getMessage());
            throw new Exception(e);
        }
    }

    /**
     * @param param
     * @functionName deleteByIDList
     * @description delete main category by list of ID
     **/
    @PostMapping(value = "/deleteByListId")
    public ResponseEntity<DataResponse<ReturnYNDto>> deleteByIDList(@RequestBody RequestData<IDVo> param) {
        DataResponse<ReturnYNDto> response  = new DataResponse<>();
        HeaderDto header                    = param.getHeader();
        IDVo listId                     = param.getBody();
        MainCategoryDto mainCategoryDto     = new MainCategoryDto();
        TransactionStatus transactionStatus    = transactionManager.getTransaction(new DefaultTransactionDefinition());

        try{
            if(listId.getList().size() > 0) {
                for ( IdDto idDto: listId.getList()) {
                    if (idDto.getId() != 0) {
                        mainCategoryDto.setId(idDto.getId());
                        mainCategoryDto.setStatus(Status.Delete.getValueStr());
                        Long delete = mainCategoryService.delete(mainCategoryDto);
                    }
                }

                transactionManager.commit(transactionStatus);
                response.setHeader(header);
                response.setBody(new ReturnYNDto(true, SYN.Y.getValue()));
                return new ResponseEntity<>(response, HttpStatus.OK);

            }
        }catch (Exception e) {
            log.error("\n get error category get list by Id ===>>>:", e.getMessage());
            transactionManager.rollback(transactionStatus);
           throw  e;
        }

        msg = Translator.toLocale( header.getLanguageCode(),"ID_LIST_ERROR");
        header.setResult(false);
        header.setMsg(msg);
        response.setHeader(header);
        response.setBody(new ReturnYNDto(true, SYN.Y.getValue()));

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * @param mainCategoryDto,LanguageCode
     * @functionName isValid
     *
     * @description Check valid data from client side
     **/
    private boolean isValid(MainCategoryDto mainCategoryDto, String LanguageCode) {
        if (mainCategoryDto.getMainCategoryName().trim() == "" || mainCategoryDto.getMainCategoryName() == null) {
            msg = Translator.toLocale( LanguageCode,"Main_Category_Name_Required");
            return false;
        }
        return true;
    }

    }
