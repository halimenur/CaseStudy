package com.example.demoJWT.controller;

import com.example.demoJWT.dto.KisiRequest;
import com.example.demoJWT.dto.KisiResponse;
import com.example.demoJWT.entity.Kisi;
import com.example.demoJWT.service.KisiService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/kisi")
@RequiredArgsConstructor
public class KisiController {

    private final KisiService service;

    // sadece ADMIN kişi ekleyebilir
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/ekle")
    public ResponseEntity<KisiResponse> kisiEkle(@Valid @RequestBody KisiRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(req));
    }

    // herkes listeleme yapabilir
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/liste")
    public ResponseEntity<Page<Kisi>> kisiListele(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return ResponseEntity.ok(service.getAll(page, size, sortBy));
    }

    // herkes belirli meslek sorgusu yapabilir
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/meslek/{meslekId}")
    public ResponseEntity<Page<Kisi>> getByMeslek(
            @PathVariable Long meslekId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return ResponseEntity.ok(service.getByMeslek(meslekId, page, size, sortBy));
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/ad")
    public ResponseEntity<List<KisiResponse>> getByAd(@RequestParam String ad) {
        return ResponseEntity.ok(service.getByAd(ad));
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/tc")
    public ResponseEntity<Page<KisiResponse>> getByTcPrefix(
            @RequestParam String prefix,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return ResponseEntity.ok(service.getByTcPrefix(prefix, page, size, sortBy));
    }
}