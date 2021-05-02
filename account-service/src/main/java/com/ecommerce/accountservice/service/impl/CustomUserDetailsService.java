package com.ecommerce.accountservice.service.impl;

import com.ecommerce.accountservice.domain.ApplicationUser;
import com.ecommerce.accountservice.repository.ApplicationUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final ApplicationUserRepository applicationUserRepository;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        ApplicationUser user = applicationUserRepository.findByUsername(s);

        if(user == null)  throw new  UsernameNotFoundException("Kullanici bulunamadi");
        return user;
    }

    @Transactional
    public ApplicationUser loadUserById(UUID userId){
        ApplicationUser user = applicationUserRepository.getByApplicationUserId(userId);
        if(user == null)  throw new  UsernameNotFoundException("Kullanici bulunamadi");
        return user;
    }
}
