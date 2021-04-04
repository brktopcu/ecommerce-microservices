package com.ecommerce.accountservice.service;

import com.ecommerce.accountservice.domain.ApplicationUser;

import java.util.UUID;

public interface ApplicationUserService {

    ApplicationUser getUserById(UUID id);
    ApplicationUser getUserByUsername(String username);
    ApplicationUser saveApplicationUser(ApplicationUser user);
}
