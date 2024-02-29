package com.example.ghandbk.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AgendaPaymentService {

    private final FornecedorService fornecedorService;
    private final ObjectMapper objectMapper;
    private final UsuarioService usuarioService;
}
