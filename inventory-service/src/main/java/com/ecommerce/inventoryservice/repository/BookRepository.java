package com.ecommerce.inventoryservice.repository;


import com.ecommerce.inventoryservice.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {

    //@Query("select x from Book x where x.bookName=:name")
    Optional<Book> findByBookName(String name);

}
