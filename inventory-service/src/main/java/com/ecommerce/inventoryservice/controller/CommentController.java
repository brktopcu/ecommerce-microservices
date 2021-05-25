package com.ecommerce.inventoryservice.controller;

import com.ecommerce.inventoryservice.domain.Comment;
import com.ecommerce.inventoryservice.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/new/{bookId}")
    public ResponseEntity<?> addNewComment(@Valid @RequestBody Comment comment
            ,Principal principal
            ,@PathVariable UUID bookId){

        Comment comment1 = commentService.saveComment(comment, principal.getName(), bookId);
        return new ResponseEntity<>(comment1, HttpStatus.CREATED);
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<Comment>> getBookComments(@PathVariable UUID bookId){
        return new ResponseEntity<>(commentService.getBookComments(bookId),HttpStatus.OK);
    }
}
