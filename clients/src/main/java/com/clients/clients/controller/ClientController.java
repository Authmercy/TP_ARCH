package com.clients.clients.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.clients.clients.dto.ClientCreateDTO;
import com.clients.clients.dto.ClientResponseDTO;
import com.clients.clients.service.ClientService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/clients") 
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    // GET ALL 
    @GetMapping
    public ResponseEntity<List<ClientResponseDTO>> getAllClients() {
        List<ClientResponseDTO> clients = clientService.getAll();
        return ResponseEntity.ok(clients);
    }

    //  GET BY ID 
    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> getClientById(@PathVariable Long id) {
        ClientResponseDTO client = clientService.getById(id);
        return ResponseEntity.ok(client);
    }

    //  CREATE 
    @PostMapping
    public ResponseEntity<ClientResponseDTO> createClient(@RequestBody ClientCreateDTO dto) {
        ClientResponseDTO created = clientService.create(dto);
        return ResponseEntity.ok(created);
    }

    //  UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> updateClient(@PathVariable Long id,
                                                          @RequestBody ClientCreateDTO dto) {
        ClientResponseDTO updated = clientService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    // DELETE 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}
