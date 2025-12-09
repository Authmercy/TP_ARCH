package com.Clients.Clients.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientCreateDTO {
    private Long id;

    @NotBlank
    private String prenom;
    @NotBlank
    private String nom;
    private String adresse;
    private String telephone;
    @Email
    private String email;
    private String statut;
}
