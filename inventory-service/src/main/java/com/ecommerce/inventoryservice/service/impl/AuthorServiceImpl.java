package com.ecommerce.inventoryservice.service.impl;

import com.ecommerce.inventoryservice.domain.Author;
import com.ecommerce.inventoryservice.domain.Book;
import com.ecommerce.inventoryservice.repository.AuthorRepository;
import com.ecommerce.inventoryservice.repository.BookRepository;
import com.ecommerce.inventoryservice.service.AuthorService;
import com.ecommerce.inventoryservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final BookService bookService;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Autowired
    public AuthorServiceImpl(BookService bookService, AuthorRepository authorRepository, BookRepository bookRepository) {
        this.bookService = bookService;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public Book saveBookAuthors(UUID bookId, List<Author> authorList) {
        Book book1 = bookRepository.findById(bookId).get();


        for (Author author : authorList) {

            if(author.getAuthorName() != null && !author.getAuthorName().equals("")){

                Optional< Author > author1 = authorRepository.findByAuthorName(author.getAuthorName());

                author1.ifPresentOrElse((authorFound) -> {

                    authorFound.getAuthorBooksList().add(book1);
                    bookRepository.save(book1);
                }, () -> {
                    if (author.getAuthorName() != null) {
                        Author newAuthor = new Author();
                        newAuthor.setAuthorName(author.getAuthorName());
                        if (author.getAuthorBio() != null) newAuthor.setAuthorBio(author.getAuthorBio());
                        System.out.println(author.getAuthorName());
                        if (author.getAuthorThumbnail() != null) newAuthor.setAuthorThumbnail(author.getAuthorThumbnail());
                        newAuthor.getAuthorBooksList().add(book1);
                        authorRepository.save(newAuthor);
                    }
                });
            }}


        return bookRepository.findById(bookId).get();
    }
}
