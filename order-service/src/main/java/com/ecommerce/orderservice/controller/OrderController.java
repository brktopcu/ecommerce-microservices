package com.ecommerce.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final Environment env;

    @Autowired
    public OrderController(Environment env) {
        this.env = env;
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello from order controller" + env.getProperty("local.server.port");
    }
}
