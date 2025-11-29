package com.clients.clients.repository;

import java.util.Optional;                      
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.clients.clients.entity.Clients;

@Repository 
public interface ClientRepository extends JpaRepository<Clients, Long> {
    Optional<Clients> findByEmail(String email);
}
