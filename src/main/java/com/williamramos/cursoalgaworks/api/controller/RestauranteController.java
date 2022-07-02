package com.williamramos.cursoalgaworks.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.williamramos.cursoalgaworks.api.model.RestauranteWrapper;
import com.williamramos.cursoalgaworks.domain.exception.EntidadeEmUsoException;
import com.williamramos.cursoalgaworks.domain.exception.EntidadeNaoEncontradaException;
import com.williamramos.cursoalgaworks.domain.exception.NegocioException;
import com.williamramos.cursoalgaworks.domain.model.Restaurante;
import com.williamramos.cursoalgaworks.domain.service.RestauranteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
    @Autowired
    private RestauranteService service;

    @GetMapping()
    public ResponseEntity<List<Restaurante>> listar() {
        List<Restaurante> restaurantes = service.listarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(restaurantes);
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<RestauranteWrapper> listarXML() {
        RestauranteWrapper obj = new RestauranteWrapper(service.listarTodos());
        return ResponseEntity.status(HttpStatus.OK).body(obj);
    }

    @GetMapping("/{id}")
    public Restaurante buscar(@PathVariable Long id) {
       return service.buscar(id);
    }
    @GetMapping("/consultar-por-nome-taxa-frete")
    public List<Restaurante> consultaPorNomeTaxaFrete(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal){
        return service.buscarPorNomeTaxaFrete(nome, taxaFreteInicial,taxaFreteFinal);
    }

    @GetMapping("/restaurante-com-frete-gratis")
    public List<Restaurante> restauranteComFreteGratis(String nome){
        return service.restauranteComFreteGratis(nome);
    }

    @PostMapping()
    public ResponseEntity<Restaurante> salvar(@RequestBody Restaurante restaurante) {
        try{
            Restaurante rest = service.salvar(restaurante);
            return ResponseEntity.ok(rest);
        }catch (EntidadeNaoEncontradaException e){
            throw new NegocioException(e.getMessage());
        }
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<?> salvar(@PathVariable Long id, @RequestBody Restaurante restaurante) {
//
//
//    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizaParcial(@PathVariable Long id, @RequestBody Map<String, Object> campos) {
        try {
            Restaurante restaurante = service.buscar(id);
            return ResponseEntity.ok().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {
        try {
            service.remover(id);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    public void merge(Map<String , Object> campos, Restaurante restaurante){
        ObjectMapper mapa = new ObjectMapper();
        Restaurante restauranteOrigem = mapa.convertValue(campos, Restaurante.class);
        campos.forEach((indice, valor)-> {
            Field atributo = ReflectionUtils.findField(Restaurante.class, indice);
            atributo.setAccessible(true);

            Object novoValor = ReflectionUtils.getField(atributo, restauranteOrigem);
            ReflectionUtils.setField(atributo, restaurante, novoValor);
        });
    }


}
