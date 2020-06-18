package com.spring.adminlte.resController.admin;

import com.spring.adminlte.dao.SubCateDetailsDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/api/subDetails")
public class SubCateDetailsRestController {
    private static final Logger log = LoggerFactory.getLogger(CompanyRestController.class);
    @Autowired
    private SubCateDetailsDao subCateDetailsDao;

    @GetMapping(value = "/get")
    public List<LinkedHashMap<String, Object>> getSubCateDetails() {
        try{
            List<LinkedHashMap<String, Object>> list = subCateDetailsDao.getSubCateDetails();
            return list;
        }catch (Exception e) {
            throw e;
        }
    }

}
