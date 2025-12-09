package com.Comptes.Comptes.service;

import com.Comptes.Comptes.entity.Compte;
import com.Comptes.Comptes.repository.ComptesRepository;



import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompteService {

    private final CompteRepository repo;

    public CompteService(ComptesRepository repo) {
        this.repo = repo;
    }

    // Conversion Entity -> DTO
    public CompteDTO toDto(Compte c){
        CompteDTO dto = new CompteDTO();
        dto.setId(c.getId());
        dto.setNumeroCompte(c.getNumeroCompte());
        dto.setClientId(c.getClientId());
        dto.setType(c.getType() == null ? null : c.getType().name());
        dto.setSolde(c.getSolde());
        dto.setStatut(c.getStatut() == null ? null : c.getStatut().name());
        return dto;
    }

    // Conversion DTO -> Entity
    public Compte toEntity(CompteDTO dto){
        Compte c = new Compte();
        c.setId(dto.getId());
        c.setNumeroCompte(dto.getNumeroCompte());
        c.setClientId(dto.getClientId());
        c.setType(dto.getType() == null ? null : TypeCompte.valueOf(dto.getType()));
        c.setSolde(dto.getSolde() == null ? BigDecimal.ZERO : dto.getSolde());
        c.setStatut(dto.getStatut() == null ? StatutCompte.ACTIF : StatutCompte.valueOf(dto.getStatut()));
        return c;
    }

    @Transactional
    public CompteDTO create(CompteDTO dto){
        Compte c = toEntity(dto);
        c.setCreatedAt(LocalDateTime.now());
        Compte saved = repo.save(c);
        return toDto(saved);
    }

    public CompteDTO getById(Long id){
        return repo.findById(id).map(this::toDto).orElse(null);
    }

    public List<CompteDTO> getByClientId(Long clientId){
        return repo.findByClientId(clientId).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Transactional
    public CompteDTO crediter(String numeroCompte, BigDecimal montant){
        Compte c = repo.findByNumeroCompte(numeroCompte)
                .orElseThrow(() -> new RuntimeException("Compte introuvable"));
        c.setSolde(c.getSolde().add(montant));
        c.setUpdatedAt(LocalDateTime.now());
        return toDto(repo.save(c));
    }

    @Transactional
    public CompteDTO debiter(String numeroCompte, BigDecimal montant){
        Compte c = repo.findByNumeroCompte(numeroCompte)
                .orElseThrow(() -> new RuntimeException("Compte introuvable"));
        if(c.getSolde().compareTo(montant) < 0) throw new RuntimeException("Solde insuffisant");
        c.setSolde(c.getSolde().subtract(montant));
        c.setUpdatedAt(LocalDateTime.now());
        return toDto(repo.save(c));
    }
}

