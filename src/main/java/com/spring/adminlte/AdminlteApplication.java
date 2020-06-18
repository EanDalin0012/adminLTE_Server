package com.spring.adminlte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdminlteApplication {

    public static void main(String[] args) {
        System.out.println("\033[31;1m Hello \033[32;1;2m world! ");
        SpringApplication.run(AdminlteApplication.class, args);
    }

}
