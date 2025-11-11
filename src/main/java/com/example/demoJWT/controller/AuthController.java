package com.example.demoJWT.controller;

import com.example.demoJWT.dto.LoginRequest;
import com.example.demoJWT.dto.RegisterRequest;
import com.example.demoJWT.service.AuthService;
import com.example.demoJWT.entity.Role;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@Valid @RequestBody RegisterRequest req) {
        String token = authService.registerAndReturnToken(req, Role.USER);
        return ResponseEntity.ok(Map.of("token", token));
    }


    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody LoginRequest request) {
        String token = authService.login(request);
        return ResponseEntity.ok(Map.of("token", token));
    }

    //İlk admin oluşturmak için bu endpoint herkese açık kalacak
    @PostMapping("/register-admin")
    public ResponseEntity<String> registerAdmin(@Valid @RequestBody RegisterRequest request) {
        authService.registerAndReturnToken(request, Role.ADMIN);
        return ResponseEntity.ok("İlk ADMIN oluşturuldu!!!");
    }
}
