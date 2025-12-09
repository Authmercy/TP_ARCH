package com.Clients.Clients.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Clients.Clients.dto.ClientCreateDTO;
import com.Clients.Clients.dto.ClientDTO;
import com.Clients.Clients.service.ClientService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/clients") 
@RequiredArgsConstructor
public class ClientController {

    private final ClientService service;
    public ClientController(ClientService service){ this.service = service; }

    @PostMapping
    public ResponseEntity<ClientDTO> create(@Valid @RequestBody ClientDTO dto){
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> update(@PathVariable Long id, @Valid @RequestBody ClientDTO dto){
        return ResponseEntity.ok(service.update(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> get(@PathVariable Long id){
        ClientDTO dto = service.getById(id);
        if(dto==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<ClientDTO>> list(){ return ResponseEntity.ok(service.listAll()); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}