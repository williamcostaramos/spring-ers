package com.williamramos.cursoalgaworks.api.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.williamramos.cursoalgaworks.api.converter.Converter;
import com.williamramos.cursoalgaworks.api.model.dto.RestauranteDTO;
import com.williamramos.cursoalgaworks.api.model.input.RestauranteInput;
import com.williamramos.cursoalgaworks.api.model.view.RestauranteView;
import com.williamramos.cursoalgaworks.domain.exception.*;
import com.williamramos.cursoalgaworks.domain.model.Restaurante;
import com.williamramos.cursoalgaworks.domain.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
    @Autowired
    private RestauranteService service;

    @Autowired
    private SmartValidator validator;

    @Autowired
    private Converter<Restaurante, RestauranteDTO> converter;



    @GetMapping()
    public ResponseEntity<List<RestauranteDTO>> listar() {
        List<RestauranteDTO> restaurantes = converter.toDTOList(service.listarTodos());
        return ResponseEntity.status(HttpStatus.OK).body(restaurantes);
    }

    @GetMapping("/{id}")
    public RestauranteDTO buscar(@PathVariable Long id) throws IllegalAccessException {

        return converter.toDTO(service.buscar(id));
    }

    @GetMapping("/consultar-por-nome-taxa-frete")
    public List<RestauranteDTO> consultaPorNomeTaxaFrete(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
        return converter.toDTOList(service.buscarPorNomeTaxaFrete(nome, taxaFreteInicial, taxaFreteFinal));
    }

    @GetMapping("/restaurante-com-frete-gratis")
    public List<RestauranteDTO> restauranteComFreteGratis(String nome) {
        return converter.toDTOList(service.restauranteComFreteGratis(nome));
    }

    @PostMapping()
    public ResponseEntity<RestauranteDTO> salvar(@RequestBody @Valid RestauranteInput restauranteInput) {
        try {
            Restaurante restaurante = converter.inputToDomain(restauranteInput);
            RestauranteDTO rest = converter.toDTO(service.salvar(restaurante));
            return ResponseEntity.ok(rest);
        } catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> salvar(@PathVariable Long id, @RequestBody @Valid RestauranteInput restaurante) {
        try {
            Restaurante restauranteAtual = service.buscar(id);
            converter.copyToDomainObject(restaurante, restauranteAtual);
            restauranteAtual = service.salvar(restauranteAtual);
            return ResponseEntity.ok(converter.toDTO(restauranteAtual));

        } catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {
        try {
            service.remover(id);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException e) {
            throw new RestauranteNaoEncontradoException(id);
        } catch (EntidadeEmUsoException e) {
            throw new RestauranteEmUsoException(id);
        }
    }

    @PutMapping("/{id}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativar(@PathVariable Long id) {
        service.ativar(id);
    }

    @DeleteMapping("/{id}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativar(@PathVariable Long id) {
        service.inativar(id);
    }

    @PutMapping("/{id}/aberto")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void abrir(@PathVariable Long id) {
        service.abrir(id);
    }

    @PutMapping("/{id}/fechado")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void fechar(@PathVariable Long id) {
        service.fechar(id);
    }

    @PutMapping("/ativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativarTodos(@RequestBody List<Long> ids){
        service.ativar(ids);
    }
    @DeleteMapping("/ativacoes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desativarTodos(@RequestBody List<Long> ids){
        service.desativar(ids);
    }

}
