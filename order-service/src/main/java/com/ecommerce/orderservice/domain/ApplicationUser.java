package com.ecommerce.orderservice.domain;

import lombok.*;

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
