package com.example.demoJWT.service;

import com.example.demoJWT.entity.Meslek;
import com.example.demoJWT.repository.MeslekRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MeslekService {
    private final MeslekRepository repo;

   
  // sayfala ve sırala
    public Page<Meslek> getAll(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return repo.findAll(pageable);
    }

    public Meslek save(Meslek meslek) {
        return repo.save(meslek);
    }
}
