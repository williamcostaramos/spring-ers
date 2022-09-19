package com.williamramos.cursoalgaworks.api.controller;

import com.williamramos.cursoalgaworks.api.converter.Converter;
import com.williamramos.cursoalgaworks.api.model.dto.CozinhaDTO;
import com.williamramos.cursoalgaworks.api.model.CozinhaWrapper;
import com.williamramos.cursoalgaworks.api.model.input.CozinhaInput;
import com.williamramos.cursoalgaworks.domain.exception.*;
import com.williamramos.cursoalgaworks.domain.model.Cozinha;
import com.williamramos.cursoalgaworks.domain.service.CozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {
    @Autowired
    private CozinhaService service;
    @Autowired
    private Converter<Cozinha, CozinhaDTO> converter;

    @GetMapping()
    public ResponseEntity<List<CozinhaDTO>> listar() {
        try {
            List<CozinhaDTO> cozinhas = converter.toDTOList(service.listarTodas());
            return ResponseEntity.ok(cozinhas);
        } catch (CozinhaNaoEncontradaException e) {
            throw new CozinhaNaoEncontradaException(e.getMessage());
        }
    }
    
    @GetMapping("/{id}")
    public Cozinha buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @GetMapping(value = "/consultar-por-nome", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<CozinhaDTO>> consultarPorNome(@RequestParam(name = "nome") String nome) {
        try {
            List<CozinhaDTO> cozinhas = converter.toDTOList(service.consultarPorNome(nome));
            return ResponseEntity.ok(cozinhas);
        } catch (CozinhaNaoEncontradaException e) {
            throw new CozinhaNaoEncontradaException(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<CozinhaDTO> salvar(@RequestBody @Valid CozinhaInput input) {
        try {
            Cozinha cozinha = converter.inputToDomain(input);
            return ResponseEntity.ok(converter.toDTO(service.salvar(cozinha)));
        } catch (Exception e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CozinhaDTO> salvar(@PathVariable Long id, @RequestBody @Valid CozinhaInput input) {
        try {
            Cozinha cozinha = converter.inputToDomain(input);
            converter.copyToDomainObject(input, cozinha);
            return ResponseEntity.ok(converter.toDTO(service.salvar(cozinha)));
        } catch (CozinhaNaoEncontradaException e) {
            throw new CozinhaNaoEncontradaException(id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {
        try {
            service.remover(id);
            return ResponseEntity.noContent().build();
        } catch (CozinhaNaoEncontradaException e) {
            throw new CozinhaNaoEncontradaException(id);
        } catch (EntidadeEmUsoException e) {
            throw new CozinhaEmUsoException(id);
        }

    }


}
