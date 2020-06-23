package com.spring.adminlte.api.restcontroller.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "web/api")
public class WebRestController {
    public void index() {
        System.out.println("api for phone");
    }
}
