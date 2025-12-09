package com.Comptes.Comptes.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.Instant;


@Entity
@Table(name="comptes")
public class Compte {
  @Id @GeneratedValue
  private Long id;
  private String numeroCompte;
  private Long clientId;
  @Enumerated(EnumType.STRING)
  private TypeCompte type; // COURANT, EPARGNE
  private BigDecimal solde;
  @Enumerated(EnumType.STRING)
  private StatutCompte statut; // ACTIF, BLOQUE
  private LocalDateTime createdAt;

}

