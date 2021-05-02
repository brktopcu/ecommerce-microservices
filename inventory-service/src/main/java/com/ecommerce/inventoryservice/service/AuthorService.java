package com.ecommerce.inventoryservice.service;

import com.ecommerce.inventoryservice.domain.Author;
import com.ecommerce.inventoryservice.domain.Book;

import java.util.List;
import java.util.UUID;

public interface AuthorService {
    Book saveBookAuthors(UUID bookId, List<Author> authorList);
}
