package com.consultacredito.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.consultacredito.model.Credito;

public interface CreditoRepository extends JpaRepository<Credito, Long> {
	List<Credito> findByNumeroNfse(String numeroNfse);
    Optional<Credito> findByNumeroCredito(String numeroCredito);
}
