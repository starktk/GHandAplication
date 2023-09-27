package com.example.GHand.service;

import com.example.GHand.document.agenda.Agenda;
import com.example.GHand.dto.agenda.AgendaDto;
import com.example.GHand.dto.agenda.AgendaRequestDto;
import com.example.GHand.dto.fornecedor.FornecedorDto;
import com.example.GHand.dto.usuario.UsuarioDto;
import com.example.GHand.repository.AgendaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Service
public class AgendaService {

    private final AgendaRepository agendaRepository;
    private final UsuarioService usuarioService;
    private final FornecedorService fornecedorService;

    private final ObjectMapper objectMapper;

    public AgendaDto addProductToReceive(AgendaRequestDto agendaRequestDto) {
        UsuarioDto userToFind = usuarioService.findUser(agendaRequestDto.getUsername());

        if (userToFind == null) {
            throw new RuntimeException("Usuario inexistente");
        }
        FornecedorDto fornecedorToFind = fornecedorService.findFornecedor(agendaRequestDto.getRazaoSocial());
        if (fornecedorToFind == null) {
            throw new RuntimeException("Fornecedor não existe!!");
        }


        LocalDate dateToReceive = LocalDate.parse(agendaRequestDto
                .getProduto().getDateToReceiveOrReceived(), DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        Agenda agendaToSave = new Agenda();
        agendaToSave.setRazaoSocial(agendaRequestDto.getRazaoSocial());
        agendaToSave.setUsername(agendaRequestDto.getUsername());
        agendaToSave.setMounth(LocalDate.from(dateToReceive.getMonth()));
        agendaToSave.setDataToReceived(dateToReceive);
        agendaToSave.setProduto(agendaRequestDto.getProduto());

        AgendaDto agendaDto = objectMapper.convertValue(agendaRepository.save(agendaToSave), AgendaDto.class);
        return agendaDto;
    }
}
