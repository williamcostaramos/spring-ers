package com.williamramos.cursoalgaworks.api.controller;

import com.williamramos.cursoalgaworks.api.converter.Converter;
import com.williamramos.cursoalgaworks.api.model.dto.GrupoDTO;
import com.williamramos.cursoalgaworks.api.model.input.GrupoInput;
import com.williamramos.cursoalgaworks.domain.exception.EntidadeEmUsoException;
import com.williamramos.cursoalgaworks.domain.exception.GrupoEmUsoException;
import com.williamramos.cursoalgaworks.domain.exception.GrupoNaoEncontradoException;
import com.williamramos.cursoalgaworks.domain.model.Grupo;
import com.williamramos.cursoalgaworks.domain.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/grupos")
public class GrupoController {
    @Autowired
    private GrupoService service;

    @Autowired
    private Converter<Grupo, GrupoDTO> converter;

    @GetMapping()
    public ResponseEntity<List<GrupoDTO>> listar() {
        List<GrupoDTO> Grupos = converter.toDTOList(service.listar());
        if (Grupos != null) {
            return ResponseEntity.status(HttpStatus.OK).body(Grupos);
        }
        return ResponseEntity.notFound().build();
    }



    @GetMapping("/{id}")
    public ResponseEntity<GrupoDTO> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(converter.toDTO(service.buscar(id)));
    }



    @PostMapping
    public ResponseEntity<GrupoDTO> salvar(@RequestBody @Valid GrupoInput input) {
        Grupo grupo = converter.dtoToDomain(input);
        if (grupo != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(converter.toDTO(service.salvar(grupo)));
        }
        return ResponseEntity.internalServerError().build();
    }

    @PutMapping("/{id}")
    public GrupoDTO salvar(@PathVariable Long id, @RequestBody GrupoInput input) {
        Grupo grupo = service.buscar(id);
        converter.copyToDomainObject(input, grupo );
        return converter.toDTO(service.salvar(grupo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {
        try {
            service.remover(id);
            return ResponseEntity.noContent().build();
        } catch (GrupoNaoEncontradoException e) {
            throw new GrupoNaoEncontradoException(id);
        } catch (EntidadeEmUsoException e) {
            throw new GrupoEmUsoException(id);
        }

    }


}
