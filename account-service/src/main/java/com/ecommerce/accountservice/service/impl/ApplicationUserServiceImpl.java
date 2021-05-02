package com.ecommerce.accountservice.service;

import com.ecommerce.accountservice.domain.ApplicationUser;
import com.ecommerce.accountservice.exception.UsernameAlreadyExistsException;
import com.ecommerce.accountservice.repository.ApplicationUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ApplicationUserServiceImpl implements ApplicationUserService {

    private final ApplicationUserRepository applicationUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

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
            return applicationUserRepository.save(newUser);
        }catch (Exception err) {
            throw new UsernameAlreadyExistsException("Username '" + newUser.getUsername() + "' already exists");
        }
    }
}
