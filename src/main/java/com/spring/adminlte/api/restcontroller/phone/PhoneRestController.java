package com.spring.adminlte.api.restcontroller.phone;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "phone/api")
public class PhoneRestController {
    public void index() {
        System.out.println("api for phone");
    }
}
