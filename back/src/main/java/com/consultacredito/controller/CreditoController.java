package com.consultacredito.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.consultacredito.model.Credito;
import com.consultacredito.service.CreditoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/creditos")
public class CreditoController {

	private final CreditoService service;

    public CreditoController(CreditoService service) {
        this.service = service;
    }
    
    @Operation(summary = "Busca Nfse", description = "Retorna resultado com a Nfse fornecida.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Nfse encontrado"),
        @ApiResponse(responseCode = "404", description = "Dados não encontrados")
    })
    @GetMapping("/{numeroNfse}")
    public ResponseEntity<?> buscarPorNfse(@PathVariable String numeroNfse) {
        try {
            List<Credito> creditos = service.consultarPorNfse(numeroNfse);
            return ResponseEntity.ok(creditos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("erro", e.getMessage()));
        }
    }

    @Operation(summary = "Busca Credito", description = "Retorna resultado com o numero de credito fornecido.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Crédito encontrado"),
        @ApiResponse(responseCode = "404", description = "Dados não encontrados")
    })
    @GetMapping("/credito/{numeroCredito}")
    public ResponseEntity<?> buscarPorNumeroCredito(@PathVariable String numeroCredito) {
        try {
            Credito credito = service.consultarPorNumeroCredito(numeroCredito);
            return ResponseEntity.ok(credito);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(Map.of("erro", e.getMessage()));
        }
    }
}
