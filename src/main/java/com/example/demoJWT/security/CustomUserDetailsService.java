package com.example.demoJWT.security;

import com.example.demoJWT.repository.KullaniciRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final KullaniciRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var k = repo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Kullanıcı bulunamadı: " + username));

        var authority = switch (k.getRole()) {
            case ADMIN -> "ROLE_ADMIN";
            case USER -> "ROLE_USER";
        };

        return User.withUsername(k.getUsername())
                .password(k.getPassword())
                .authorities(authority)
                .build();
    }
}
