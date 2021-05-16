package com.ecommerce.orderservice.repository;

import com.ecommerce.orderservice.domain.BookOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookOrderRepository extends JpaRepository<BookOrder, UUID> {
}
