package com.clients.clients.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientUpdateDTO {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String telephone;
    private String statut;
    private String adresse;
}
