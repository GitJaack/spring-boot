package com.mycontact.mycontact.model.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginDTO {
    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Format d'email invalide")
    @Schema(example = "test1@gmail.com")
    private String email;

    @NotBlank(message = "Le mot de passe est obligatoire")
    @Size(min = 4, max = 24, message = "Le mot de passe doit contenir entre 4 et 24 caractères")
    @Schema(example = "1234")
    private String password;

}
