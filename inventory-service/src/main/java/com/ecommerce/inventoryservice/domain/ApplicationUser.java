package com.ecommerce.inventoryservice.domain;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationUser{

    private UUID applicationUserId;
    private String username;
    private String fullName;
    private String userAddress;

    private String password;
    private String confirmPassword;
    private Long userPhoneNumber;

}
