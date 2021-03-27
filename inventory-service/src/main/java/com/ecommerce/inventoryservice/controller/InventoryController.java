package com.ecommerce.inventoryservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final Environment env;

    @Autowired
    public InventoryController(Environment env) {
        this.env = env;
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello from inventory controller" + env.getProperty("local.server.port");
    }
}
