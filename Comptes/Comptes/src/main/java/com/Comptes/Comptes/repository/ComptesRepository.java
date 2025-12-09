package com.Comptes.Comptes.repository;

import com.Comptes.Comptes.entity.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
public interface ComptesRepository extends JpaRepository<Compte, Long> {
   Optional<Compte> findByNumeroCompte(String numero);
    List<Compte> findByClientId(Long clientId);
}
