package com.ecommerce.accountservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account/")
public class AccountController {

    @GetMapping("/hello")
    public String hello(){
        return "Hello from account controller";
    }
}
