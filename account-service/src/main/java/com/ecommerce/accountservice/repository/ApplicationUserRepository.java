package com.ecommerce.accountservice.repository;

import com.ecommerce.accountservice.domain.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, UUID> {

    ApplicationUser findByUsername(String username);
    ApplicationUser getById(UUID userId);

}
