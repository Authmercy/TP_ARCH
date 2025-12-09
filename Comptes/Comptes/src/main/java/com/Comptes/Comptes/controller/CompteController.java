package com.Comptes.Comptes.controller;

import com.Comptes.Comptes.dto.CompteDTO;
import com.Comptes.Comptes.service.CompteService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/comptes")
public class CompteController {

    private final CompteService service;

    public CompteController(CompteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CompteDTO> create(@Valid @RequestBody CompteDTO dto){
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompteDTO> getById(@PathVariable Long id){
        CompteDTO dto = service.getById(id);
        if(dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<CompteDTO>> getByClient(@PathVariable Long clientId){
        return ResponseEntity.ok(service.getByClientId(clientId));
    }

    @PostMapping("/{numero}/credit")
    public ResponseEntity<CompteDTO> crediter(@PathVariable String numero, @RequestParam BigDecimal montant){
        return ResponseEntity.ok(service.crediter(numero, montant));
    }

    @PostMapping("/{numero}/debit")
    public ResponseEntity<CompteDTO> debiter(@PathVariable String numero, @RequestParam BigDecimal montant){
        return ResponseEntity.ok(service.debiter(numero, montant));
    }
}

