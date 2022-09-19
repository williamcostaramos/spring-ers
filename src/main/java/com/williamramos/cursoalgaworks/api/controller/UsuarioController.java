package com.williamramos.cursoalgaworks.api.controller;

import com.williamramos.cursoalgaworks.api.converter.Converter;
import com.williamramos.cursoalgaworks.api.model.dto.UsuarioDTO;
import com.williamramos.cursoalgaworks.api.model.input.SenhaInput;
import com.williamramos.cursoalgaworks.api.model.input.UsuarioInput;
import com.williamramos.cursoalgaworks.api.model.input.UsuarioSenhaInput;
import com.williamramos.cursoalgaworks.domain.exception.*;
import com.williamramos.cursoalgaworks.domain.model.Usuario;
import com.williamramos.cursoalgaworks.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService service;
    @Autowired
    private Converter<Usuario, UsuarioDTO> converter;

    @GetMapping()
    public ResponseEntity<List<UsuarioDTO>> listar() {
        List<UsuarioDTO> usuarios = converter.toDTOList(service.listar());
        if (usuarios != null) {
            return ResponseEntity.status(HttpStatus.OK).body(usuarios);
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/{id}")
    public Usuario buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> salvar(@RequestBody @Valid UsuarioSenhaInput input) {
        try {
            Usuario usuario = converter.inputToDomain(input);
            return ResponseEntity.ok(converter.toDTO(service.salvar(usuario)));
        } catch (GrupoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> salvar(@PathVariable Long id, @RequestBody @Valid  UsuarioInput input) {
        try {
            Usuario usuarioAtual = service.buscar(id);
            converter.copyToDomainObject(input, usuarioAtual);
            return ResponseEntity.ok(converter.toDTO(service.salvar(usuarioAtual)));
        }catch (NegocioException e){
            throw  new NegocioException(e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {
        try {
            service.remover(id);
            return ResponseEntity.noContent().build();
        } catch (UsuarioNaoEncontradoException e) {
            throw new UsuarioNaoEncontradoException(id);
        } catch (EntidadeEmUsoException e) {
            throw new UsuarioEmUsoException(id);
        }

    }

    @PutMapping("/{id}/ativacao")
    public ResponseEntity<?> ativar(@PathVariable Long id) {
        try {
            service.ativar(id);
            return ResponseEntity.noContent().build();
        } catch (UsuarioNaoEncontradoException e) {
            throw new UsuarioNaoEncontradoException(id);
        } catch (EntidadeEmUsoException e) {
            throw new UsuarioEmUsoException(id);
        }

    }

    @DeleteMapping("/{id}/ativacao")
    public ResponseEntity<?> inativar(@PathVariable Long id) {
        try {
            service.inativar(id);
            return ResponseEntity.noContent().build();
        } catch (UsuarioNaoEncontradoException e) {
            throw new UsuarioNaoEncontradoException(id);
        } catch (EntidadeEmUsoException e) {
            throw new UsuarioEmUsoException(id);
        }

    }

    @PutMapping("/{id}/alteracao-senha")
    public void alterarSenha(@PathVariable Long id, @RequestBody SenhaInput input) {
        try {
            service.alterarSenha(id, input.getSenhaAtual(), input.getSenhaNova());
        } catch (NegocioException e) {
            throw new NegocioException(e.getMessage());
        }

    }


}
