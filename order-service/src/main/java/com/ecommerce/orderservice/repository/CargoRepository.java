package com.ecommerce.orderservice.repository;

import com.ecommerce.orderservice.domain.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, UUID> {
}
