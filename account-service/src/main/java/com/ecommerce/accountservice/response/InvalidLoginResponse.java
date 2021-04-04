package com.ecommerce.accountservice.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidLoginResponse {
    private String username;
    private String password;

    public InvalidLoginResponse(){
        this.username = "Kullanici adi gecersiz";
        this.password = "Parola gecersiz";
    }
}
