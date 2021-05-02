package com.ecommerce.inventoryservice.service;

import com.ecommerce.inventoryservice.domain.Book;
import com.ecommerce.inventoryservice.domain.ShoppingCart;
import com.ecommerce.inventoryservice.response.BookSizeResponse;
import com.ecommerce.inventoryservice.response.TotalPriceResponse;

import java.util.List;
import java.util.UUID;

public interface ShoppingCartService {
    ShoppingCart addBookToCard(UUID bookID, String username);

    void setPrice(ShoppingCart shoppingCart);

    List<BookSizeResponse> userShoppingCart(String username);

    ShoppingCart getShoppingCartById(UUID shoppingCartId);

    TotalPriceResponse getTotalPrice(String username);

    List<Book> decreaseBookOrderFromCard(UUID bookId, String username);

    void create(String username);
}
