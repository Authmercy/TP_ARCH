package com.Clients.Clients.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.Clients.Clients.dto.ClientCreateDTO;
import com.Clients.Clients.dto.ClientResponseDTO;
import com.Clients.Clients.entity.Clients;
import com.Clients.Clients.repository.ClientRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientService {


    private final ClientRepository repo;
    public ClientService(ClientRepository repo) { this.repo = repo; }

    public ClientDTO toDto(Client c){
        ClientDTO dto = new ClientDTO();
        dto.setId(c.getId());
        dto.setPrenom(c.getPrenom());
        dto.setNom(c.getNom());
        dto.setAdresse(c.getAdresse());
        dto.setTelephone(c.getTelephone());
        dto.setEmail(c.getEmail());
        dto.setStatut(c.getStatut() == null ? null : c.getStatut().name());
        return dto;
    }

    public Client toEntity(ClientDTO dto){
        Client c = new Client();
        c.setId(dto.getId());
        c.setPrenom(dto.getPrenom());
        c.setNom(dto.getNom());
        c.setAdresse(dto.getAdresse());
        c.setTelephone(dto.getTelephone());
        c.setEmail(dto.getEmail());
        c.setStatut(dto.getStatut() == null ? null : com.willbank.clients.entity.StatutClient.valueOf(dto.getStatut()));
        return c;
    }

    @Transactional
    public ClientDTO create(ClientDTO dto){
        Client c = toEntity(dto);
        c.setCreatedAt(LocalDateTime.now());
        Client saved = repo.save(c);
        return toDto(saved);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto){
        Client existing = repo.findById(id).orElseThrow(() -> new RuntimeException("Client introuvable"));
        existing.setPrenom(dto.getPrenom());
        existing.setNom(dto.getNom());
        existing.setAdresse(dto.getAdresse());
        existing.setTelephone(dto.getTelephone());
        existing.setEmail(dto.getEmail());
        if(dto.getStatut()!=null) existing.setStatut(com.willbank.clients.entity.StatutClient.valueOf(dto.getStatut()));
        existing.setUpdatedAt(LocalDateTime.now());
        return toDto(repo.save(existing));
    }

    public ClientDTO getById(Long id){
        return repo.findById(id).map(this::toDto).orElse(null);
    }

    public List<ClientDTO> listAll(){
        return repo.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public void delete(Long id){ repo.deleteById(id); }
}