package com.consultacredito;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import com.consultacredito.model.Credito;
import com.consultacredito.repository.CreditoRepository;
import com.consultacredito.service.CreditoService;

@ExtendWith(MockitoExtension.class)
class CreditoServiceTest {

    @Mock
    private CreditoRepository repository;

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @InjectMocks
    private CreditoService service;

    @Test
    void consultarPorNfse_deveRetornarListaDeCreditos() {
        List<Credito> creditos = List.of(new Credito(1L, "123", "CR1"));
        Mockito.when(repository.findByNumeroNfse("123")).thenReturn(creditos);

        List<Credito> resultado = service.consultarPorNfse("123");

        Assertions.assertEquals(1, resultado.size());
        Mockito.verify(kafkaTemplate).send("consulta-creditos", "Consulta por NFS-e: 123");
    }

    @Test
    void consultarPorNumeroCredito_deveRetornarCredito() {
        Credito credito = new Credito(1L, "123", "CR1");
        Mockito.when(repository.findByNumeroCredito("CR1")).thenReturn(Optional.of(credito));

        Credito resultado = service.consultarPorNumeroCredito("CR1");

        Assertions.assertEquals("CR1", resultado.getNumeroCredito());
        Mockito.verify(kafkaTemplate).send("consulta-creditos", "Consulta por crédito: CR1");
    }

    @Test
    void consultarPorNumeroCredito_deveLancarExcecaoQuandoNaoEncontrado() {
        Mockito.when(repository.findByNumeroCredito("X")).thenReturn(Optional.empty());

        Assertions.assertThrows(RuntimeException.class, () -> service.consultarPorNumeroCredito("X"));
    }
}
