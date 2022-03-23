package com.williamramos.cursoalgaworks.api.controller;

import com.williamramos.cursoalgaworks.domain.model.Restaurante;
import com.williamramos.cursoalgaworks.domain.repository.RestauranteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
    @Autowired
    private RestauranteRepository repository;

    @GetMapping()
    public ResponseEntity<List<Restaurante>> listar() {
        List<Restaurante> restaurantes = repository.listAll();
        return ResponseEntity.status(HttpStatus.OK).body(restaurantes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscar(@PathVariable Long id) {
        Restaurante obj = repository.findById(id);
        if (obj != null) {
            return ResponseEntity.status(HttpStatus.OK).body(obj);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping()
    public ResponseEntity<Restaurante> salvar(@RequestBody Restaurante restaurante) {
        Restaurante obj = repository.salvar(restaurante);
        if (obj != null) {
            return ResponseEntity.status(HttpStatus.OK).body(obj);
        }
        return ResponseEntity.internalServerError().body(obj);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurante> salvar(@PathVariable Long id, @RequestBody Restaurante restaurante) {
        Restaurante obj = repository.findById(id);
        if (obj != null) {
            BeanUtils.copyProperties(restaurante, obj, "id");
            repository.salvar(obj);
            return ResponseEntity.status(HttpStatus.OK).body(obj);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Restaurante> remover(@PathVariable Long id) {
        Restaurante obj = repository.findById(id);
        if (obj != null) {
            repository.remover(obj);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.notFound().build();
    }


}
