package com.example.ghandbk.service;

import com.example.ghandbk.collection.enums.SituacaoProduto;
import com.example.ghandbk.collection.schedule.AgendaProduto;
import com.example.ghandbk.dto.schedule.AgendaProdDto;
import com.example.ghandbk.dto.schedule.AgendaProdutoRequestDto;
import com.example.ghandbk.dto.supllier.FornecedorDto;
import com.example.ghandbk.dto.supllier.FornecedorRequestDto;
import com.example.ghandbk.dto.user.UsuarioRequestDto;
import com.example.ghandbk.exceptions.InvalidValueException;
import com.example.ghandbk.exceptions.NotAuthorizedException;
import com.example.ghandbk.exceptions.NotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AgendaService {

    private final FornecedorService fornecedorService;
    private final UsuarioService usuarioService;
    private final ObjectMapper objectMapper;

    public AgendaProduto insertNewSchedule(AgendaProdutoRequestDto agendaProdutoRequestDto) throws InvalidValueException, NotFoundException, NotAuthorizedException {
        if (agendaProdutoRequestDto.getCnpj() == null || agendaProdutoRequestDto.getCnpj().isBlank()) throw new InvalidValueException("Cnpj inválido");
        if (agendaProdutoRequestDto.getAmount() <= 0) throw new InvalidValueException("Quantidade inválida");
        if (agendaProdutoRequestDto.getNameProduct() == null || agendaProdutoRequestDto.getNameProduct().isBlank()) throw new InvalidValueException("Preencha o campo");
        if (agendaProdutoRequestDto.getStatus() == SituacaoProduto.RECEBIDO) throw new InvalidValueException("Situação inválida");
        AgendaProduto agendaProduto = objectMapper.convertValue(agendaProdutoRequestDto, AgendaProduto.class);
        FornecedorRequestDto fornecedorRequestDto = new FornecedorRequestDto();
        fornecedorRequestDto.setUsername(agendaProdutoRequestDto.getUsername());
        fornecedorRequestDto.setCnpj(agendaProdutoRequestDto.getCnpj());
        FornecedorDto fornecedorDto = fornecedorService.getFornecedorByCnpj(fornecedorRequestDto);
        agendaProduto.setFornecedor(fornecedorDto);
        UsuarioRequestDto user = new UsuarioRequestDto();
        user.setUsername(agendaProdutoRequestDto.getUsername());
        user.setName(agendaProdutoRequestDto.getName());
        user.setAgendaProduto(agendaProduto);
        usuarioService.updateUser(user);
        return agendaProduto;
    }

    public List<AgendaProdDto> findAgendaByMonth(AgendaProdutoRequestDto agendaProdutoRequestDto) throws InvalidValueException, NotFoundException {
        if (agendaProdutoRequestDto.getDateToPayOrReceive().getMonth() == null) throw new InvalidValueException("Data inválida");
        List<AgendaProduto> agendaProdutos = usuarioService.getAgendaProdutcs(agendaProdutoRequestDto.getUsername());
        List<AgendaProduto> agenda = agendaProdutos.stream().filter(prod -> prod.getDateToPayOrReceive().getMonth().equals(agendaProdutoRequestDto.getDateToPayOrReceive().getMonth())).toList();
        if (agenda.isEmpty()) throw new NotFoundException("Não foram encontrados recebimentos para o mês selecionado");
        List<AgendaProdDto> agendaToReturn = agenda.stream().map(agendaP -> new AgendaProdDto(agendaP.getNameProduct(), agendaP.getAmount(), agendaP.getStatus(), agendaP.getFornecedor())).toList();
        return agendaToReturn;
    }

    public void deleteReceive(AgendaProdutoRequestDto agendaProdutoRequestDto) throws InvalidValueException, NotFoundException {
        if (agendaProdutoRequestDto.getDateToPayOrReceive().getMonth() == null) throw new InvalidValueException("Data inválida");
        if (agendaProdutoRequestDto.getNameProduct().isEmpty()) throw new InvalidValueException("Nome do produto inválido");
        if (agendaProdutoRequestDto.getAmount() == 0) throw new InvalidValueException("Quantidade inválida");
        if (agendaProdutoRequestDto.getCnpj().isEmpty() || agendaProdutoRequestDto.getCnpj().length() <= 11) throw new InvalidValueException("Cnpj inválido");
        AgendaProduto agenda = objectMapper.convertValue(agendaProdutoRequestDto, AgendaProduto.class);
        FornecedorRequestDto fornecedorRequestDto = new FornecedorRequestDto();
        fornecedorRequestDto.setUsername(agendaProdutoRequestDto.getUsername());
        fornecedorRequestDto.setCnpj(agendaProdutoRequestDto.getCnpj());
        FornecedorDto fornecedor = fornecedorService.getFornecedorByCnpj(fornecedorRequestDto);
        agenda.setFornecedor(fornecedor);
        UsuarioRequestDto user = new UsuarioRequestDto();
        user.setUsername(agendaProdutoRequestDto.getUsername());
        user.setAgendaProduto(agenda);
    }


}
