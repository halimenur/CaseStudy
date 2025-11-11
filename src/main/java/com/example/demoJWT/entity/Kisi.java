package com.example.demoJWT.entity;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Kisi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Ad alanı boş olamaz!!")
    @Size(min = 2, max = 50, message = "Ad 2 ile 50 karakter arasında olmalıdır")  
    private String ad;
    
    @NotBlank(message = "Soyad alanı boş olamaz!")
    @Size(min = 2, max = 50, message = "Soyad 2 ile 50 karakter arasında olmalıdır")
    private String soyad;

    @NotBlank(message = "TC kimlik numarası boş olamaz!")
    @Pattern(regexp = "\\d{11}", message = "TC kimlik numarası 11 haneli olmalıdır")
    private String tcKimlikNo;

    @ManyToOne
    @JoinColumn(name = "meslek_id")
    private Meslek meslek;
}
