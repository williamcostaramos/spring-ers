package com.williamramos.cursoalgaworks.api.controller;

import com.williamramos.cursoalgaworks.api.converter.RestauranteConverter;
import com.williamramos.cursoalgaworks.api.model.CozinhaDTO;
import com.williamramos.cursoalgaworks.api.model.RestauranteDTO;
import com.williamramos.cursoalgaworks.api.model.RestauranteWrapper;
import com.williamramos.cursoalgaworks.api.model.input.RestauranteInput;
import com.williamramos.cursoalgaworks.domain.exception.CozinhaNaoEncontradaException;
import com.williamramos.cursoalgaworks.domain.exception.EntidadeEmUsoException;
import com.williamramos.cursoalgaworks.domain.exception.EntidadeNaoEncontradaException;
import com.williamramos.cursoalgaworks.domain.exception.NegocioException;
import com.williamramos.cursoalgaworks.domain.model.Cozinha;
import com.williamramos.cursoalgaworks.domain.model.Restaurante;
import com.williamramos.cursoalgaworks.domain.service.RestauranteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
    @Autowired
    private RestauranteService service;

    @Autowired
    private SmartValidator validator;

    @Autowired
    private RestauranteConverter converter;


    @GetMapping()
    public ResponseEntity<List<RestauranteDTO>> listar() {
        List<RestauranteDTO> restaurantes = converter.toDTOCollect(service.listarTodos());
        return ResponseEntity.status(HttpStatus.OK).body(restaurantes);
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<RestauranteWrapper> listarXML() {
        RestauranteWrapper obj = new RestauranteWrapper(service.listarTodos());
        return ResponseEntity.status(HttpStatus.OK).body(obj);
    }

    @GetMapping("/{id}")
    public RestauranteDTO buscar(@PathVariable Long id) throws IllegalAccessException {

        return converter.toDTO(service.buscar(id));
    }

    @GetMapping("/consultar-por-nome-taxa-frete")
    public List<RestauranteDTO> consultaPorNomeTaxaFrete(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
        return converter.toDTOCollect(service.buscarPorNomeTaxaFrete(nome, taxaFreteInicial, taxaFreteFinal));
    }

    @GetMapping("/restaurante-com-frete-gratis")
    public List<RestauranteDTO> restauranteComFreteGratis(String nome) {
        return converter.toDTOCollect(service.restauranteComFreteGratis(nome));
    }

    @PostMapping()
    public ResponseEntity<RestauranteDTO> salvar(@RequestBody @Valid RestauranteInput restauranteInput) {
        try {
            Restaurante restaurante = converter.inputToDomain(restauranteInput);
            RestauranteDTO rest = converter.toDTO(service.salvar(restaurante));
            return ResponseEntity.ok(rest);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> salvar(@PathVariable Long id, @RequestBody @Valid RestauranteInput restaurante) {
        try {
            Restaurante restauranteAtual = service.buscar(id);
            BeanUtils.copyProperties(restaurante, restauranteAtual,
                    "id", "formasPagamento", "endereco", "dataCadastro", "produtos");
            service.salvar(restauranteAtual);
            return ResponseEntity.ok(converter.toDTO(restauranteAtual));

        } catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
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

}
