package com.williamramos.cursoalgaworks.api.controller;

import com.williamramos.cursoalgaworks.api.converter.Converter;
import com.williamramos.cursoalgaworks.api.model.dto.ResponsavelDTO;
import com.williamramos.cursoalgaworks.domain.exception.NegocioException;
import com.williamramos.cursoalgaworks.domain.exception.RestauranteNaoEncontradoException;
import com.williamramos.cursoalgaworks.domain.exception.UsuarioNaoEncontradoException;
import com.williamramos.cursoalgaworks.domain.model.Restaurante;
import com.williamramos.cursoalgaworks.domain.model.Usuario;
import com.williamramos.cursoalgaworks.domain.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes/{idRestaurante}/responsaveis")
public class RestauranteResponsavelController {
    @Autowired
    private RestauranteService service;
    @Autowired
    private Converter<Usuario, ResponsavelDTO> converter;

    @GetMapping()
    public ResponseEntity<List<ResponsavelDTO>> listar(@PathVariable Long idRestaurante) {
        Restaurante Restaurante = service.buscar(idRestaurante);
        return ResponseEntity.ok(converter.toDTOList(Restaurante.getResponsaveis()));
    }


    @PutMapping("/{idUsuario}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associarResponsavel(@PathVariable Long idRestaurante, @PathVariable Long idUsuario) {
        try {
            service.associarResponsavel(idRestaurante, idUsuario);
        } catch (RestauranteNaoEncontradoException | UsuarioNaoEncontradoException e) {
            throw new NegocioException(e.getMessage());
        }

    }

    @DeleteMapping("/{idUsuario}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociarResponsavel(@PathVariable Long idRestaurante, @PathVariable Long idUsuario) {
        try {
            this.service.desassociarResponsavel(idRestaurante, idUsuario);
        } catch (RestauranteNaoEncontradoException | UsuarioNaoEncontradoException e) {
            throw new NegocioException(e.getMessage());
        }

    }


}
