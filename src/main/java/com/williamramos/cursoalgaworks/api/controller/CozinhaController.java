package com.williamramos.cursoalgaworks.api.controller;

import com.williamramos.cursoalgaworks.api.model.CozinhaWrapper;
import com.williamramos.cursoalgaworks.domain.exception.EntidadeEmUsoException;
import com.williamramos.cursoalgaworks.domain.exception.EntidadeNaoEncontradaException;
import com.williamramos.cursoalgaworks.domain.model.Cozinha;
import com.williamramos.cursoalgaworks.domain.service.CozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {
    @Autowired
    private CozinhaService service;

    @GetMapping()
    public ResponseEntity<List<Cozinha>> listar() {
        List<Cozinha> cozinhas = service.listarTodas();
        if (cozinhas != null) {
            return ResponseEntity.status(HttpStatus.OK).body(cozinhas);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<CozinhaWrapper> listarXML() {
        CozinhaWrapper obj = new CozinhaWrapper(service.listarTodas());
        if (obj != null) {
            return ResponseEntity.status(HttpStatus.OK).body(obj);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Cozinha> buscar(@PathVariable Long id) {
        Optional<Cozinha> obj = service.buscar(id);
        if (obj.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(obj.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @GetMapping(value = "/consultar-por-nome", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> consultarPorNome(@RequestParam(name = "nome") String nome) {
        List<Cozinha> cozinha = service.consultarPorNome(nome);
        if (cozinha.size() ==0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cozinha);


    }

    @PostMapping
    public ResponseEntity<Cozinha> salvar(@RequestBody Cozinha cozinha) {
        Cozinha obj = service.salvar(cozinha);
        if (obj != null) {

            return ResponseEntity.status(HttpStatus.CREATED).body(obj);
        }
        return ResponseEntity.internalServerError().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cozinha> salvar(@PathVariable Long id, @RequestBody Cozinha cozinha) {
        Optional<Cozinha> obj = service.buscar(id);
        if (obj.isPresent()) {
            BeanUtils.copyProperties(cozinha, obj.get(), "id");
            Cozinha cozinhaAtualizada = service.salvar(obj.get());
            return ResponseEntity.status(HttpStatus.OK).body(cozinhaAtualizada);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity remover(@PathVariable Long id) {
        try {
            service.remover(id);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

    }

}
