package com.ecommerce.inventoryservice.response;

import com.ecommerce.inventoryservice.domain.Cargo;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TotalPriceResponse {
    private BigDecimal totalPrice;
    private BigDecimal price;
    private Cargo cargo;
}
