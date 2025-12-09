package com.Comptes.Comptes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CompteDTO {

    private Long id;

    @NotBlank
    private String numeroCompte;

    @NotNull
    private Long clientId;

    @NotNull
    private String type; // COURANT / EPARGNE

    private BigDecimal solde;

    private String statut; // ACTIF / BLOQUE

}
