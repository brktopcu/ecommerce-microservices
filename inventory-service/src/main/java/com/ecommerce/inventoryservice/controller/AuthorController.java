package com.ecommerce.inventoryservice.controller;

import com.ecommerce.inventoryservice.domain.Author;
import com.ecommerce.inventoryservice.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/author")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/book/{bookId}")
    public ResponseEntity<?> saveBookAuthor(
            @PathVariable UUID bookId,
            @RequestBody List<Author> authorList){

        return new ResponseEntity<>(authorService.saveBookAuthors(bookId,authorList), HttpStatus.OK);

    }
}
