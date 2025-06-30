package com.consultacredito.service;

import java.util.List;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.consultacredito.model.Credito;
import com.consultacredito.repository.CreditoRepository;

@Service
public class CreditoService {
	private final CreditoRepository repository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public CreditoService(CreditoRepository repository, KafkaTemplate<String, String> kafkaTemplate) {
        this.repository = repository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public List<Credito> consultarPorNfse(String numeroNfse) {
        List<Credito> creditos = repository.findByNumeroNfse(numeroNfse);
        kafkaTemplate.send("consulta-creditos", "Consulta por NFS-e: " + numeroNfse);
        return creditos;
    }

    public Credito consultarPorNumeroCredito(String numeroCredito) {
        Credito credito = repository.findByNumeroCredito(numeroCredito)
            .orElseThrow(() -> new RuntimeException("Crédito não encontrado"));
        kafkaTemplate.send("consulta-creditos", "Consulta por crédito: " + numeroCredito);
        return credito;
    }
}
