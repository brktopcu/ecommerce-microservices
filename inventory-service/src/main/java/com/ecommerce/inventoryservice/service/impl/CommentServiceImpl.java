package com.ecommerce.inventoryservice.service.impl;

import com.ecommerce.inventoryservice.domain.ApplicationUser;
import com.ecommerce.inventoryservice.domain.Book;
import com.ecommerce.inventoryservice.domain.Comment;
import com.ecommerce.inventoryservice.exception.NotFoundException;
import com.ecommerce.inventoryservice.repository.BookRepository;
import com.ecommerce.inventoryservice.repository.CommentRepository;
import com.ecommerce.inventoryservice.service.BookService;
import com.ecommerce.inventoryservice.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;
    private final BookService bookService;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, BookRepository bookRepository, BookService bookService) {
        this.commentRepository = commentRepository;
        this.bookRepository = bookRepository;
        this.bookService = bookService;
    }

    @Override
    public Comment getCommentsByBookId(UUID id) {
        return   commentRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Comment Not Found"));

    }

    @Override
    public Comment saveComment(Comment comment, String username, UUID bookId) {

        var newComment = Comment.builder()
                .commentDescription(comment.getCommentDescription())
                .rate(comment.getRate()).build();

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new NotFoundException("Book Not Found"));


        bookService.saveBookRate(BigDecimal.valueOf(newComment.getRate()),book.getBookId());
        book.getBookCommentList().add(newComment);

        newComment.setBook(book);
        newComment.setUsername(username);

        bookRepository.save(book);


        return newComment;

    }

    @Override
    public String deleteCommentById(UUID id) {

        Comment comment= commentRepository.findById(id).orElseThrow(() -> new NotFoundException("Comment Not Found"));

        commentRepository.delete(comment);

        return "Comment Deleted";
    }

    @Override
    public List<Comment> getBookComments(UUID bookId) {

        var book= bookService.getBookById(bookId);
        return book.getBookCommentList();
    }
}
