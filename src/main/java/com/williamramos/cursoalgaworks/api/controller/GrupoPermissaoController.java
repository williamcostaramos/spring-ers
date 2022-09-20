package com.williamramos.cursoalgaworks.api.controller;

import com.williamramos.cursoalgaworks.api.converter.Converter;
import com.williamramos.cursoalgaworks.api.model.dto.GrupoDTO;
import com.williamramos.cursoalgaworks.api.model.dto.PermissaoDTO;
import com.williamramos.cursoalgaworks.domain.exception.*;
import com.williamramos.cursoalgaworks.domain.model.Grupo;
import com.williamramos.cursoalgaworks.domain.model.Permissao;
import com.williamramos.cursoalgaworks.domain.model.Usuario;
import com.williamramos.cursoalgaworks.domain.service.GrupoService;
import com.williamramos.cursoalgaworks.domain.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupos/{idGrupo}/permissoes")
public class GrupoPermissaoController {
    @Autowired
    private GrupoService service;
    @Autowired
    private Converter<Permissao, PermissaoDTO> converter;

    @GetMapping()
    public ResponseEntity<List<PermissaoDTO>> listar(@PathVariable Long idGrupo) {
        Grupo grupo = service.buscar(idGrupo);
        return ResponseEntity.ok(converter.toDTOList(grupo.getPermissoes()));
    }


    @GetMapping("/{idPermisao}")
    public PermissaoDTO buscar(@PathVariable Long idPermissao) {
        return converter.toDTO(service.buscar(idPermissao));
    }


    @PutMapping("/{idPermisao}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associarGrupo(@PathVariable Long idGrupo, @PathVariable Long idPermisao) {
        try {
            service.associarPermissao(idGrupo, idPermisao);
        } catch (GrupoNaoEncontradoException | PermissaoNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }

    }

    @DeleteMapping("/{idPermisao}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociarGrupo(@PathVariable Long idGrupo, @PathVariable Long idPermisao) {
        try {
            this.service.desassociarPermissao(idGrupo, idPermisao);
        } catch (GrupoNaoEncontradoException | PermissaoNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }

    }


}
