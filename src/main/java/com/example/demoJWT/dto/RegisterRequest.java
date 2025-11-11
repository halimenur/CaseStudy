package com.example.demoJWT.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank private String username;

    @NotBlank
    @Size(min = 6, message = "Şifre en az 6 karakter olmalı")
    private String password;
    
    @NotBlank
    private String role;
}
