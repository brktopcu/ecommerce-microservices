package com.ecommerce.inventoryservice.service.impl;

import com.ecommerce.inventoryservice.domain.Book;
import com.ecommerce.inventoryservice.domain.Category;
import com.ecommerce.inventoryservice.repository.CategoryRepository;
import com.ecommerce.inventoryservice.service.BookService;
import com.ecommerce.inventoryservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final BookService bookService;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, BookService bookService) {
        this.categoryRepository = categoryRepository;
        this.bookService = bookService;
    }


    @Override
    public Book setBookCategory(UUID bookId, List<Category> categoryList) {
        Book book = bookService.getBookById(bookId);

        for(Category category : categoryList){

            Optional<Category> category1 = categoryRepository.findByCategoryDescription(category.getCategoryDescription());

            category1.ifPresentOrElse((cat)->{
                cat.getBookCategoryList().add(book);
                categoryRepository.save(cat);
            },() -> {
                Category newCategory = new Category();
                newCategory.setCategoryDescription(category.getCategoryDescription());
                newCategory.getBookCategoryList().add(book);
                categoryRepository.save(newCategory);
            } );

        }

        return book ;
    }
}
