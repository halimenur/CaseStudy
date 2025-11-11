package com.example.demoJWT.repository;

import com.example.demoJWT.entity.Kullanici;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KullaniciRepository extends JpaRepository<Kullanici, Long> {
    Optional<Kullanici> findByUsername(String username);
    boolean existsByUsername(String username);
}
