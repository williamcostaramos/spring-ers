package com.williamramos.cursoalgaworks.api.controller;

import com.williamramos.cursoalgaworks.api.converter.Converter;
import com.williamramos.cursoalgaworks.api.model.dto.CidadeDTO;
import com.williamramos.cursoalgaworks.api.model.input.CidadeInput;
import com.williamramos.cursoalgaworks.domain.exception.*;
import com.williamramos.cursoalgaworks.domain.model.Cidade;
import com.williamramos.cursoalgaworks.domain.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {
    @Autowired
    private CidadeService service;

    @Autowired
    private Converter<Cidade, CidadeDTO> converter;

    @GetMapping
    public List<CidadeDTO> listar() {
        return converter.toDTOList(service.listarTodos());
    }

    @GetMapping({"/{id}"})
    public CidadeDTO buscar(@PathVariable Long id) {
        return converter.toDTO(service.buscar(id));
    }

    @PostMapping
    public ResponseEntity<CidadeDTO> salvar(@RequestBody @Valid CidadeInput cidadeInput) {
        try {
            Cidade cidade = converter.inputToDomain(cidadeInput);
            return ResponseEntity.ok(converter.toDTO(service.salvar(cidade)));
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<CidadeDTO> atualizar(@PathVariable Long id, @RequestBody @Valid CidadeInput cidadeInput) {
        try {
            Cidade cidadeAtual = service.buscar(id);
            converter.copyToDomainObject(cidadeInput, cidadeAtual);
            return ResponseEntity.ok(converter.toDTO(service.salvar(cidadeAtual)));
        }catch (EstadoNaoEncontradoException | CidadeNaoEncontradaException e){
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
            throw new CidadeEmUsoException(id);
        }
    }

}
