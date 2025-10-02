package com.mycontact.mycontact.model.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ContactDTO {
    @NotBlank(message = "Le prénom est obligatoire")
    @Size(max = 12, message = "Le prénom ne doit pas dépasser 12 caractères")
    @Schema(example = "test")
    private String nom;

    @NotBlank(message = "Le nom est obligatoire")
    @Size(max = 12, message = "Le nom ne doit pas dépasser 12 caractères")
    @Schema(example = "test1")
    private String prenom;

    @NotBlank(message = "Le numéro de téléphone est obligatoire")
    @Pattern(regexp = "\\d{10}", message = "Le numéro de téléphone doit contenir 10 chiffres")
    @Schema(example = "1234567890")
    private String phone;
}
