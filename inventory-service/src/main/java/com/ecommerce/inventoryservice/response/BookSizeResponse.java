package com.ecommerce.inventoryservice.response;

import com.ecommerce.inventoryservice.domain.Book;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookSizeResponse {

    private Book book;
    private Integer count;
}
