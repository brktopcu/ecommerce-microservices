package com.ecommerce.inventoryservice.controller;

import com.ecommerce.inventoryservice.domain.Category;
import com.ecommerce.inventoryservice.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/setCategory/{bookId}")
    ResponseEntity<?> setBookCategory(@Valid @RequestBody List<Category> categoryList,
                                      @PathVariable UUID bookId){

        return new  ResponseEntity<>(categoryService.setBookCategory(bookId,categoryList), HttpStatus.OK);
    }
}
