package com.clients.clients.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.clients.clients.dto.ClientCreateDTO;
import com.clients.clients.dto.ClientResponseDTO;
import com.clients.clients.entity.Clients;
import com.clients.clients.repository.ClientRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    // GET ALL
    public List<ClientResponseDTO> getAll() {
        return clientRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    // GET BY ID
    public ClientResponseDTO getById(Long id) {
        Clients client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        return mapToResponseDTO(client);
    }

    // CREATE
    public ClientResponseDTO create(ClientCreateDTO dto) {
        Clients client = mapToEntity(dto);
        Clients saved = clientRepository.save(client);
        return mapToResponseDTO(saved);
    }

    // UPDATE
    public ClientResponseDTO update(Long id, ClientCreateDTO dto) {
        Clients client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));

        client.setName(dto.getName());
        client.setSurname(dto.getSurname());
        client.setEmail(dto.getEmail());
        client.setTelephone(dto.getTelephone());
        client.setAdresse(dto.getAdresse());
        client.setStatut(dto.getStatut());

        Clients updated = clientRepository.save(client);
        return mapToResponseDTO(updated);
    }

    // DELETE
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    // MAPPER : entity -> DTO
    private ClientResponseDTO mapToResponseDTO(Clients client) {
        return new ClientResponseDTO(
                client.getId(),
                client.getName(),
                client.getSurname(),
                client.getEmail(),
                client.getTelephone(),
                client.getStatut(),
                client.getAdresse()
        );
    }

    // MAPPER : DTO -> entity
    private Clients mapToEntity(ClientCreateDTO dto) {
        return new Clients(
                null, 
                dto.getName(),
                dto.getSurname(),
                dto.getEmail(),
                dto.getTelephone(),
                dto.getStatut(),
                dto.getAdresse()
        );
    }
}
