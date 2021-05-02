package com.ecommerce.accountservice.service.impl;

import com.ecommerce.accountservice.domain.ApplicationUser;
import com.ecommerce.accountservice.exception.UsernameAlreadyExistsException;
import com.ecommerce.accountservice.repository.ApplicationUserRepository;
import com.ecommerce.accountservice.service.ApplicationUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ApplicationUserServiceImpl implements ApplicationUserService {

    private final ApplicationUserRepository applicationUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RestTemplate restTemplate;

    @Override
    public ApplicationUser getUserById(UUID id) {
        return null;
    }

    @Override
    public ApplicationUser getUserByUsername(String username) {
        return null;
    }

    @Override
    public ApplicationUser saveApplicationUser(ApplicationUser newUser) {
        try {
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            newUser.setConfirmPassword("");

            //String CREATE_SHOPPING_CART_URL = "http://localhost:8082/inventory-service/api/shoppingCart/new/";
            //restTemplate.getForObject(CREATE_SHOPPING_CART_URL +newUser.getUsername(), String.class);

            return applicationUserRepository.save(newUser);
        }catch (Exception err) {
            throw new UsernameAlreadyExistsException("Username '" + newUser.getUsername() + "' already exists");
        }
    }
}
