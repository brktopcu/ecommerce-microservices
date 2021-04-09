package com.ecommerce.accountservice.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginRequest {
    @NotBlank(message = "Kullanici adi bos olamaz")
    private String username;

    @NotBlank(message = "Parola bos olamaz")
    private String password;
}
