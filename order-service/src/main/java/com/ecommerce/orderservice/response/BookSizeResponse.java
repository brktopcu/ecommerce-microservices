package com.ecommerce.orderservice.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookSizeResponse {

    private Book book;
    private Integer count;
}
