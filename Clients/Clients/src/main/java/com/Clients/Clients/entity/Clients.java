package com.Clients.Clients.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "clients")
public class Clients {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String prenom;
    private String nom;
    private String adresse;
    private String telephone;
    private String email;

    @Enumerated(EnumType.STRING)
    private StatutClient statut; 

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
