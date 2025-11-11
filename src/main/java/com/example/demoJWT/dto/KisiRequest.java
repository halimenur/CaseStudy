package com.example.demoJWT.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
public class KisiRequest {
    private String ad;
    private String soyad;
    private String tcKimlikNo;
    private Long meslekId;
}
