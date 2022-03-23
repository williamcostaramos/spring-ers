package com.williamramos.cursoalgaworks.api.controller;

import com.williamramos.cursoalgaworks.api.model.CozinhaWrapper;
import com.williamramos.cursoalgaworks.domain.model.Cozinha;
import com.williamramos.cursoalgaworks.domain.repository.CozinhaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {
    @Autowired
    private CozinhaRepository repository;

    @GetMapping()
    public ResponseEntity<List<Cozinha>> listar() {
        List<Cozinha> cozinhas = repository.listAll();
        if (cozinhas != null) {
            return ResponseEntity.status(HttpStatus.OK).body(cozinhas);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<CozinhaWrapper> listarXML() {
        CozinhaWrapper obj = new CozinhaWrapper(repository.listAll());
        if (obj != null) {
            return ResponseEntity.status(HttpStatus.OK).body(obj);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Cozinha> buscar(@PathVariable Long id) {
        Cozinha obj = repository.finById(id);
        if (obj != null) {
            return ResponseEntity.status(HttpStatus.OK).body(obj);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @PostMapping
    public ResponseEntity<Cozinha> salvar(@RequestBody Cozinha cozinha){
          Cozinha obj = repository.salvar(cozinha);
          if(obj != null){

            return ResponseEntity.status(HttpStatus.CREATED).body(obj);
          }
          return ResponseEntity.internalServerError().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cozinha> salvar(@PathVariable Long id, @RequestBody Cozinha cozinha) {
        Cozinha obj = repository.finById(id);
        if(obj != null){
            BeanUtils.copyProperties(cozinha, obj, "id");
            obj = repository.salvar(obj);
            return ResponseEntity.status(HttpStatus.OK).body(obj);
        }
        return  ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity remover(@PathVariable Long id){
        Cozinha obj = repository.finById(id);
        if(obj != null){
            repository.remove(obj);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.notFound().build();
    }
}
