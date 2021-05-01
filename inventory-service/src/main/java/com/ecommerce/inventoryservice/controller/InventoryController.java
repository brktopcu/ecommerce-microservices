package com.ecommerce.inventoryservice.controller;

import com.ecommerce.inventoryservice.domain.Book;
import com.ecommerce.inventoryservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final Environment env;
    private final BookService bookService;

    @Autowired
    public InventoryController(Environment env, BookService bookService) {
        this.env = env;
        this.bookService = bookService;
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello from inventory controller" + env.getProperty("local.server.port");
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllBooks(){
        List<Book> allBooks = bookService.getAllBooks();
        return new ResponseEntity<>(allBooks, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveBook(@Valid @RequestBody Book book){

        return new ResponseEntity<>(bookService.saveBook(book), HttpStatus.CREATED);
    }
}
