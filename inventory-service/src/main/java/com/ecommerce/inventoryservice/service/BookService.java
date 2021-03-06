package com.ecommerce.inventoryservice.service;

import com.ecommerce.inventoryservice.domain.Book;
import com.ecommerce.inventoryservice.request.BookFiltersRequest;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface BookService {

    Book getBookById(UUID id);

    void saveBookRate(BigDecimal commentRate, UUID bookId);

    String deleteBookById(UUID id);

    Book saveBook(Book book);

    Book updateBook(Book book);

    Book setBookOrder(UUID bookId , Integer order);

    Book deleteBookOrder(UUID bookId);

    Boolean addToShoppingCart(UUID bookId);

    Book getBookByName(String bookName);

    List<Book> getAllBooks();

    List<Book> getFilteredBooks(BookFiltersRequest bookFiltersRequest);
}
