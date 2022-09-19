package com.williamramos.cursoalgaworks.api.controller;

import com.williamramos.cursoalgaworks.api.converter.Converter;
import com.williamramos.cursoalgaworks.api.model.dto.GrupoDTO;
import com.williamramos.cursoalgaworks.domain.exception.EntidadeEmUsoException;
import com.williamramos.cursoalgaworks.domain.exception.GrupoNaoEncontradoException;
import com.williamramos.cursoalgaworks.domain.exception.NegocioException;
import com.williamramos.cursoalgaworks.domain.exception.UsuarioNaoEncontradoException;
import com.williamramos.cursoalgaworks.domain.model.Grupo;
import com.williamramos.cursoalgaworks.domain.model.Usuario;
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
    private UsuarioService service;
    @Autowired
    private Converter<Grupo, GrupoDTO> converter;

    @GetMapping()
    public ResponseEntity<List<GrupoDTO>> listar(@PathVariable Long idUsuario) {
        Usuario usuario = service.buscar(idUsuario);
        return ResponseEntity.ok(converter.toDTOList(usuario.getGrupos()));
    }


    @GetMapping("/{idGrupo}")
    public GrupoDTO buscar(@PathVariable Long idGrupo) {
        return converter.toDTO(service.buscar(idGrupo));
    }


    @PutMapping("/{idGrupo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associarGrupo(@PathVariable Long idUsuario, @PathVariable Long idGrupo) {
        try {
            service.associarGrupo(idUsuario, idGrupo);
        } catch (NegocioException e) {
            throw new NegocioException(e.getMessage());
        }

    }

    @DeleteMapping("/{idGrupo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociarGrupo(@PathVariable Long idUsuario, @PathVariable Long idGrupo) {
        try {
            this.service.desassociarGrupo(idUsuario, idGrupo);
        } catch (UsuarioNaoEncontradoException e) {
            throw new UsuarioNaoEncontradoException(idUsuario);
        } catch (EntidadeEmUsoException e) {
            throw new GrupoNaoEncontradoException(idGrupo);
        }

    }


}
