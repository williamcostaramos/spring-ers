package com.williamramos.cursoalgaworks.api.controller;

import com.williamramos.cursoalgaworks.api.converter.Converter;


import com.williamramos.cursoalgaworks.api.model.dto.PermissaoDTO;
import com.williamramos.cursoalgaworks.api.model.input.PermissaoInput;
import com.williamramos.cursoalgaworks.domain.exception.*;
import com.williamramos.cursoalgaworks.domain.model.Permissao;
import com.williamramos.cursoalgaworks.domain.service.PermissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/permissoes")
public class PermissaoController {
    @Autowired
    private PermissaoService service;

    @Autowired
    private Converter<Permissao, PermissaoDTO> converter;

    @GetMapping
    public List<PermissaoDTO> listar() {
        return converter.toDTOList(service.listar());
    }

    @GetMapping({"/{id}"})
    public PermissaoDTO buscar(@PathVariable Long id) {
        return converter.toDTO(service.buscar(id));
    }

    @PostMapping
    public ResponseEntity<PermissaoDTO> salvar(@RequestBody @Valid PermissaoInput input) {
        try {
            Permissao Permisao = converter.inputToDomain(input);
            return ResponseEntity.ok(converter.toDTO(service.salvar(Permisao)));
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<PermissaoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid PermissaoInput input) {
        try {
            Permissao PermisaoAtual = service.buscar(id);
            converter.copyToDomainObject(input, PermisaoAtual);
            return ResponseEntity.ok(converter.toDTO(service.salvar(PermisaoAtual)));
        } catch (PermissaoNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        try {
            service.remover(id);
        } catch (EntidadeNaoEncontradaException e) {
            throw new PermissaoNaoEncontradaException(id);
        } catch (EntidadeEmUsoException e) {
            throw new PermissaoEmUsoException(id);
        }
    }

}
