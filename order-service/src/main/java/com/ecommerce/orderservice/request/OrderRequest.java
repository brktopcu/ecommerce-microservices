package com.ecommerce.orderservice.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderRequest {
    private String name;
    private String phone;
    private String deliveryAddress;
    private String invoiceAddress;
    private BigDecimal totalPrice;
    private BigDecimal cargoPrice;
}
