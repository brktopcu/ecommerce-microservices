package com.ecommerce.orderservice.controller;

import com.ecommerce.orderservice.request.OrderRequest;
import com.ecommerce.orderservice.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final Environment env;

    private final OrderService orderService;

    @Autowired
    public OrderController(Environment env, OrderService orderService) {
        this.env = env;
        this.orderService = orderService;
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello from order controller" + env.getProperty("local.server.port");
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveOrder(@Valid Principal principal,
                                       @RequestBody OrderRequest orderRequest,
                                       @RequestHeader("Authorization") String token) throws JsonProcessingException {

        return new ResponseEntity<>(orderService.saveOrder(orderRequest, principal.getName(), token), HttpStatus.OK);
    }
}
