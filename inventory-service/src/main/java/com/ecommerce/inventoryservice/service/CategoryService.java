package com.ecommerce.inventoryservice.service;

import com.ecommerce.inventoryservice.domain.Book;
import com.ecommerce.inventoryservice.domain.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    Book setBookCategory(UUID bookId, List<Category> categoryList);
}
