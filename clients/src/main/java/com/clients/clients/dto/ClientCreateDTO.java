package com.clients.clients.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientCreateDTO {
    @NotNull
    private String name;

    @NotNull
    private String surname;

    @Email
    private String email;

    private String telephone;
    private String adresse;
    private String statut;
}
