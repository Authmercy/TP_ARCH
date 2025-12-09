package com.Clients.Clients.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponseDTO {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String telephone;
    private String statut;
    private String adresse;
}
