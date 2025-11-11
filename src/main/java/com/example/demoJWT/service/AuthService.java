package com.example.demoJWT.service;

import com.example.demoJWT.dto.LoginRequest;
import com.example.demoJWT.dto.RegisterRequest;
import com.example.demoJWT.entity.Kullanici;
import com.example.demoJWT.entity.Role;
import com.example.demoJWT.repository.KullaniciRepository;
import com.example.demoJWT.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authManager;
    private final KullaniciRepository repo;
    private final PasswordEncoder pe;
    private final JwtUtil jwt;

    public String registerAndReturnToken(RegisterRequest req, Role role) {
        if (repo.existsByUsername(req.getUsername())) {
            throw new RuntimeException("Kullanıcı adı zaten var");
        }

        var user = Kullanici.builder()
                .username(req.getUsername())
                .password(pe.encode(req.getPassword()))
                .role(role)
                .build();
        repo.save(user);

        //Register da token oluştur
        return jwt.generate(user.getUsername());
    }

    public String login(LoginRequest r) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(r.getUsername(), r.getPassword()));
        return jwt.generate(r.getUsername());
    }
}
