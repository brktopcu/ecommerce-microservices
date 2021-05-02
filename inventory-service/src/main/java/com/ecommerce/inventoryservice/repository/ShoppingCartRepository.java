package com.ecommerce.inventoryservice.repository;

import com.ecommerce.inventoryservice.domain.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, UUID> {

    ShoppingCart findByUsername(String username);

}
