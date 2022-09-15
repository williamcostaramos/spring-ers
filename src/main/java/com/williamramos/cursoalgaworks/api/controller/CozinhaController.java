package com.williamramos.cursoalgaworks.api.controller;

import com.williamramos.cursoalgaworks.api.model.CozinhaDTO;
import com.williamramos.cursoalgaworks.api.model.CozinhaWrapper;
import com.williamramos.cursoalgaworks.domain.exception.CozinhaNaoEncontradaException;
import com.williamramos.cursoalgaworks.domain.exception.EntidadeEmUsoException;
import com.williamramos.cursoalgaworks.domain.exception.EntidadeNaoEncontradaException;
import com.williamramos.cursoalgaworks.domain.exception.NegocioException;
import com.williamramos.cursoalgaworks.domain.model.Cozinha;
import com.williamramos.cursoalgaworks.domain.service.CozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {
    @Autowired
    private CozinhaService service;

    @GetMapping()
    public ResponseEntity<List<CozinhaDTO>> listar() {
        List<CozinhaDTO> cozinhas = toDTOList(service.listarTodas());
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
    public Cozinha buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @GetMapping(value = "/consultar-por-nome", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> consultarPorNome(@RequestParam(name = "nome") String nome) {
        List<CozinhaDTO> cozinha = toDTOList(service.consultarPorNome(nome));
        if (cozinha.size() == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cozinha);


    }

    @PostMapping
    public ResponseEntity<CozinhaDTO> salvar(@RequestBody @Valid Cozinha cozinha) {
        CozinhaDTO obj = toDTO(service.salvar(cozinha));
        if (obj != null) {

            return ResponseEntity.status(HttpStatus.CREATED).body(obj);
        }
        return ResponseEntity.internalServerError().build();
    }

    @PutMapping("/{id}")
    public CozinhaDTO salvar(@PathVariable Long id, @RequestBody Cozinha cozinha) {
        Cozinha obj = service.buscar(id);
        BeanUtils.copyProperties(cozinha, obj, "id");


        return toDTO(service.salvar(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {
        try {
            service.remover(id);
            return ResponseEntity.noContent().build();
        } catch (CozinhaNaoEncontradaException e) {
            throw new CozinhaNaoEncontradaException(id);
        } catch (EntidadeEmUsoException e) {
            throw new EntidadeEmUsoException(id.toString());
        }

    }

    private CozinhaDTO toDTO(Cozinha cozinha) {
        CozinhaDTO cozinhaDTO = new CozinhaDTO();
        cozinhaDTO.setId(cozinha.getId());
        cozinhaDTO.setNome(cozinha.getNome());
        return cozinhaDTO;
    }
    private List<CozinhaDTO> toDTOList(List<Cozinha> cozinhas){
        return cozinhas.stream().map(cozinha -> toDTO(cozinha)).collect(Collectors.toList());
    }

}
