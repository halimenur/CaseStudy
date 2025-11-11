package com.example.demoJWT.repository;

import com.example.demoJWT.entity.Kisi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface KisiRepository extends JpaRepository<Kisi, Long> {
	 // Ada göre arama
    List<Kisi> findByAd(String ad);

    // TC kimlik numarası belirli bir önek ile başlayanları bul
    Page<Kisi> findByTcKimlikNoStartingWith(String prefix, Pageable pageable);

    // Belirli bir mesleğe sahip kişileri sayfalı olarak getir
    Page<Kisi> findByMeslekId(Long meslekId, Pageable pageable);
    

}
