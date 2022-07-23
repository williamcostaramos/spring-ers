package com.williamramos.cursoalgaworks.api.controller;

import com.williamramos.cursoalgaworks.domain.exception.EstadoNaoEncontradoException;
import com.williamramos.cursoalgaworks.domain.exception.NegocioException;
import com.williamramos.cursoalgaworks.domain.model.Cidade;
import com.williamramos.cursoalgaworks.domain.model.Estado;
import com.williamramos.cursoalgaworks.domain.service.CidadeService;
import com.williamramos.cursoalgaworks.domain.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {
    @Autowired
    private EstadoService service;

    @GetMapping
    public List<Estado> listar() {
        return service.listarTodos();
    }

    @GetMapping({"/id"})
    public Estado buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PostMapping
    public Estado salvar(@RequestBody  Estado estado) {

        try {
            return service.salvar(estado);
        } catch (EstadoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage(), e);
        }

    }
}
