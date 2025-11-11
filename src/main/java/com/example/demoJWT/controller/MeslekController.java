package com.example.demoJWT.controller;

import com.example.demoJWT.entity.Meslek;
import com.example.demoJWT.service.MeslekService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/meslekler")
@RequiredArgsConstructor
public class MeslekController {

    private final MeslekService service;

    //Herkes meslekleri listeleyebilir
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping
    public ResponseEntity<Page<Meslek>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return ResponseEntity.ok(service.getAll(page, size, sortBy));
    }

    //ADMIN yeni meslek ekleyebilir
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Meslek> save(@Valid @RequestBody Meslek meslek) {
        Meslek kaydedilen = service.save(meslek);
        return ResponseEntity.status(HttpStatus.CREATED).body(kaydedilen);
    }
}
