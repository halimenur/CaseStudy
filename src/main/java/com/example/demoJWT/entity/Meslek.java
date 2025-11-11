package com.example.demoJWT.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
@Entity
@JsonIgnoreProperties({"kisiler"}) // Sonsuz döngü engelleme
public class Meslek {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Meslek ismi boş olamaz!")
    @Size(min = 2, max = 100, message = "Meslek ismi 2 ile 100 karakter arasında olmalıdır")
    private String isim;
    
    @NotBlank(message = "Açıklama boş olamaz!")
    @Size(min = 5, max = 255, message = "Açıklama 5 ile 255 karakter arasında olmalıdır")
    private String aciklama;

    @OneToMany(mappedBy = "meslek", cascade = CascadeType.ALL)
    private List<Kisi> kisiler;
}
