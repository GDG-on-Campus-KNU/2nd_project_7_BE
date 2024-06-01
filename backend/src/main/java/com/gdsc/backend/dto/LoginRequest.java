package com.gdsc.backend.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true)
public class LoginRequest {
    private final String email;
    private final String password;

    public LoginRequest(String email, String password){
        this.email = email;
        this.password = password;
    }
}
