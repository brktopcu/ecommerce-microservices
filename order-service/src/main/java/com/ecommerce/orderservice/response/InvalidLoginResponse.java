package com.ecommerce.orderservice.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidLoginResponse {
    private String password;
    private String username;

    public InvalidLoginResponse() {
        this.password = "Kullanici adi gecersiz";
        this.username = "Parola gecersiz";
    }
}
