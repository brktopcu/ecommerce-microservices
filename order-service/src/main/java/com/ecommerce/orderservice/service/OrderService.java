package com.ecommerce.orderservice.service;

import com.ecommerce.orderservice.request.OrderRequest;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface OrderService {
    String saveOrder(OrderRequest orderRequest, String username, String token) throws JsonProcessingException;
}
