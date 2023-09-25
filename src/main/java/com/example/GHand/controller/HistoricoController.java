package com.example.GHand.controller;

import com.example.GHand.dto.fornecedor.HistoricoUpdateRequest;
import com.example.GHand.service.HistoricoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/historico")
public class HistoricoController {

    private final HistoricoService historicoService;
    @PutMapping("historicoUP")
    public ResponseEntity attHistorico(@RequestBody HistoricoUpdateRequest historicoUpdateRequest) {
        historicoService.addProdutoToHistorico(historicoUpdateRequest);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }
}
