package com.williamramos.cursoalgaworks.api.controller;

import com.williamramos.cursoalgaworks.api.converter.Converter;
import com.williamramos.cursoalgaworks.api.model.dto.FormaPagamentoDTO;
import com.williamramos.cursoalgaworks.api.model.input.FormaPagamentoInput;
import com.williamramos.cursoalgaworks.domain.exception.*;
import com.williamramos.cursoalgaworks.domain.model.FormaPagamento;
import com.williamramos.cursoalgaworks.domain.service.FormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController {
    @Autowired
    private FormaPagamentoService service;

    @Autowired
    private Converter<FormaPagamento, FormaPagamentoDTO> converter;

    @GetMapping
    public List<FormaPagamentoDTO> listar() {
        return converter.toDTOList(service.listar());
    }

    @GetMapping({"/{id}"})
    public FormaPagamentoDTO buscar(@PathVariable Long id) {
        return converter.toDTO(service.buscar(id));
    }

    @PostMapping
    public ResponseEntity<FormaPagamentoDTO> salvar(@RequestBody @Valid FormaPagamentoInput input) {
        try {
            FormaPagamento formaPagamento = converter.inputToDomain(input);
            return ResponseEntity.ok(converter.toDTO(service.salvar(formaPagamento)));
        } catch (FormaPagamentoNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<FormaPagamentoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid FormaPagamentoInput input) {
        try {
            FormaPagamento formaPagamentoAtual = service.buscar(id);
            converter.copyToDomainObject(input, formaPagamentoAtual);
            return ResponseEntity.ok(converter.toDTO(service.salvar(formaPagamentoAtual)));
        } catch (FormaPagamentoNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {
        try {
            service.remover(id);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException e) {
            throw new FormaPagamentoEmUsoException(id);
        }
    }

}
