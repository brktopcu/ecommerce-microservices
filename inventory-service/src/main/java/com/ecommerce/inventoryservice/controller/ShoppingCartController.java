package com.ecommerce.inventoryservice.controller;

import com.ecommerce.inventoryservice.domain.ShoppingCart;
import com.ecommerce.inventoryservice.response.BookSizeResponse;
import com.ecommerce.inventoryservice.response.TotalPriceResponse;
import com.ecommerce.inventoryservice.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/shoppingCart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping("/addBook/{bookId}")
    public ResponseEntity<?> addBookToShoppingCart(
            @PathVariable UUID bookId, Principal principal) {
        return new ResponseEntity<>(shoppingCartService.addBookToCard(bookId, principal.getName()), HttpStatus.OK);
    }

    @GetMapping("/removeBook/{bookId}")
    public ResponseEntity<?> removeBookFromShoppingCart(@PathVariable UUID bookId, Principal principal){
        return new ResponseEntity<>(shoppingCartService.decreaseBookOrderFromCard(bookId, principal.getName()),HttpStatus.OK);
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookSizeResponse>> userShoppingCartBooks(Principal principal){
        return new ResponseEntity<>(shoppingCartService.userShoppingCart(principal.getName()),HttpStatus.OK);
    }

    @GetMapping("/totalPrice")
    public ResponseEntity<TotalPriceResponse> getTotalPrice(Principal principal){
        return new ResponseEntity<>(shoppingCartService.getTotalPrice(principal.getName()),HttpStatus.OK);
    }
}
