package com.consultacredito;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import com.consultacredito.model.Credito;
import com.consultacredito.service.CreditoService;
import com.consultacredito.controller.CreditoController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;

@WebMvcTest(CreditoController.class)
class CreditoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreditoService service;

    @Test
    void buscarPorNfse_deveRetornarLista() throws Exception {
        List<Credito> creditos = List.of(new Credito(1L, "123", "CR1"));
        when(service.consultarPorNfse("123")).thenReturn(creditos);

        mockMvc.perform(get("/api/creditos/123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].numeroCredito").value("CR1"));
    }

    @Test
    void buscarPorNumeroCredito_deveRetornarCredito() throws Exception {
        Credito credito = new Credito(1L, "123", "CR2");
        when(service.consultarPorNumeroCredito("CR2")).thenReturn(credito);

        mockMvc.perform(get("/api/creditos/credito/CR2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numeroCredito").value("CR2"));
    }
}
