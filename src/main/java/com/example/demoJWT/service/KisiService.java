package com.example.demoJWT.service;

import com.example.demoJWT.dto.KisiRequest;
import com.example.demoJWT.dto.KisiResponse;
import com.example.demoJWT.entity.Kisi;
import com.example.demoJWT.entity.Meslek;
import com.example.demoJWT.repository.KisiRepository;
import com.example.demoJWT.repository.MeslekRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.*;

import java.util.List;
@Service
@RequiredArgsConstructor
public class KisiService {

    private final KisiRepository kisiRepository;
    private final MeslekRepository meslekRepository;
    
    public Kisi save(Kisi k) {
        return kisiRepository.save(k);
    }
    public KisiResponse save(KisiRequest request) {
        Meslek meslek = meslekRepository.findById(request.getMeslekId())
                .orElseThrow(() -> new RuntimeException("Meslek bulunamadı"));

        Kisi kisi = new Kisi();
        kisi.setAd(request.getAd());
        kisi.setSoyad(request.getSoyad());
        kisi.setTcKimlikNo(request.getTcKimlikNo());
        kisi.setMeslek(meslek);

        Kisi kaydedilen = kisiRepository.save(kisi);

        KisiResponse res = new KisiResponse();
        res.setId(kaydedilen.getId());
        res.setAd(kaydedilen.getAd());
        res.setSoyad(kaydedilen.getSoyad());
        res.setTcKimlikNo(kaydedilen.getTcKimlikNo());
        res.setMeslekIsim(meslek.getIsim());
        res.setMeslekAciklama(meslek.getAciklama());

        return res;
    }
    
    //Tüm kişiler sayfalı ve sıralı getir
    public Page<Kisi> getAll(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        return kisiRepository.findAll(pageable);
    }

    //Meslek ID'sine göre kişileri getir
    public Page<Kisi> getByMeslek(Long meslekId, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        return kisiRepository.findByMeslekId(meslekId, pageable);
    }

    //Ada göre arama
    public List<KisiResponse> getByAd(String ad) {
        List<Kisi> kisiler = kisiRepository.findByAd(ad);

        return kisiler.stream().map(k -> {
            KisiResponse dto = new KisiResponse();
            dto.setId(k.getId());
            dto.setAd(k.getAd());
            dto.setSoyad(k.getSoyad());
            dto.setTcKimlikNo(k.getTcKimlikNo());
            if (k.getMeslek() != null) {
                dto.setMeslekIsim(k.getMeslek().getIsim());
                dto.setMeslekAciklama(k.getMeslek().getAciklama());
            }
            return dto;
        }).toList();
    }

    //TC kimlik numarası prefix'e göre arama
    public Page<KisiResponse> getByTcPrefix(String prefix, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        Page<Kisi> kisiler = kisiRepository.findByTcKimlikNoStartingWith(prefix, pageable);

        
        List<KisiResponse> dtoList = kisiler.stream().map(k -> {
            KisiResponse dto = new KisiResponse();
            dto.setId(k.getId());
            dto.setAd(k.getAd());
            dto.setSoyad(k.getSoyad());
            dto.setTcKimlikNo(k.getTcKimlikNo());
            if (k.getMeslek() != null) {
                dto.setMeslekIsim(k.getMeslek().getIsim());
                dto.setMeslekAciklama(k.getMeslek().getAciklama());
            }
            return dto;
        }).toList();

        return  new PageImpl<>(dtoList, pageable, kisiler.getTotalElements());
}
}
