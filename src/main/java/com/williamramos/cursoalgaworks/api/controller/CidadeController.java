package com.williamramos.cursoalgaworks.api.controller;

import com.williamramos.cursoalgaworks.domain.exception.EntidadeNaoEncontradaException;
import com.williamramos.cursoalgaworks.domain.exception.EstadoNaoEncontradoException;
import com.williamramos.cursoalgaworks.domain.exception.NegocioException;
import com.williamramos.cursoalgaworks.domain.model.Cidade;
import com.williamramos.cursoalgaworks.domain.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {
    @Autowired
    private CidadeService service;

    @GetMapping
    public List<Cidade> listar() {
        return service.listarTodos();
    }

    @GetMapping({"/id"})
    public Cidade buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PostMapping
    public Cidade salvar(@RequestBody Cidade cidade) {

        try {
            return service.salvar(cidade);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }

    }
}
