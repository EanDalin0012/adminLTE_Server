package com.spring.adminlte.api.restcontroller.admin;

import com.spring.adminlte.component.Translator;
import com.spring.adminlte.constants.Status;
import com.spring.adminlte.dto.FaqDto;
import com.spring.adminlte.dto.HeaderDto;
import com.spring.adminlte.dto.vo.FaqVo;
import com.spring.adminlte.services.serviceImplements.FaqServiceImplement;
import com.spring.adminlte.core.template.classes.DataResponse;
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

@RestController
@RequestMapping(value = "/api/faq")
public class FaqRestController {

    private static final Logger log = LoggerFactory.getLogger(FaqRestController.class);

    @Autowired
    private FaqServiceImplement faqService;

    String msg = "";

    @PostMapping(value = "/getList")
    public ResponseEntity<DataResponse<FaqVo>> getList(@RequestBody HeaderDto param) {
        DataResponse<FaqVo> response = new DataResponse<>();
        try {
            FaqVo list = retreiveListFAQ();
            response.setBody(list);
            response.setHeader(param);
        }catch (Exception e) {
            log.error("\nget error api get list faq\n", e.getMessage());
            throw e;
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private FaqVo retreiveListFAQ() {
        FaqVo output = new FaqVo();
        List<FaqDto> list = faqService.getList(Status.Delete.getValueStr());
        output.setList(list);
        return output;
    }

    private boolean isValid(String lang, FaqDto param) {

        if (param.getCHTitle().trim().equals("") || param.getCHTitle().trim().isEmpty() || param.getCHTitle() == null) {
           msg = Translator.toLocale(lang, "Faq_CH");
           return false;
        }
        return true;
    }
}
