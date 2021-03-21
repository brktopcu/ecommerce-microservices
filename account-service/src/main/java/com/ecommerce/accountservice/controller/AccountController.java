package com.ecommerce.accountservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account/")
public class AccountController {

    private final Environment env;

    @Autowired
    public AccountController(Environment env) {
        this.env = env;
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello from account controller" + env.getProperty("local.server.port");
    }
}
