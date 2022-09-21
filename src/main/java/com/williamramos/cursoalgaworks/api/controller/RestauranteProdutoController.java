package com.williamramos.cursoalgaworks.api.controller;

import com.williamramos.cursoalgaworks.api.converter.Converter;
import com.williamramos.cursoalgaworks.api.model.dto.ProdutoDTO;
import com.williamramos.cursoalgaworks.api.model.input.ProdutoInput;
import com.williamramos.cursoalgaworks.domain.exception.NegocioException;
import com.williamramos.cursoalgaworks.domain.exception.ProdutoNaoEncontradoException;
import com.williamramos.cursoalgaworks.domain.exception.RestauranteNaoEncontradoException;
import com.williamramos.cursoalgaworks.domain.model.Produto;
import com.williamramos.cursoalgaworks.domain.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/restaurantes/{idRestaurante}/produtos")
public class RestauranteProdutoController {
    @Autowired
    private ProdutoService service;
    @Autowired
    private Converter<Produto, ProdutoDTO> converter;

    @GetMapping()
    public ResponseEntity<List<ProdutoDTO>> listar(@PathVariable Long idRestaurante) {
        List<Produto> produtos = service.listar(idRestaurante);
        return ResponseEntity.ok(converter.toDTOList(produtos));
    }


    @GetMapping("/{idProduto}")
    public ProdutoDTO buscar(@PathVariable Long idProduto, @PathVariable Long idRestaurante) {
        return converter.toDTO(service.buscar(idProduto, idRestaurante));
    }

    @PostMapping()
    public ResponseEntity<ProdutoDTO> salvar(@RequestBody @Valid ProdutoInput input) {
        try {
            Produto produto = converter.dtoToDomain(input);
            return ResponseEntity.ok(converter.toDTO(service.salvar(produto)));
        } catch (RestauranteNaoEncontradoException e) {
            throw new NegocioException(e.getMessage());
        }

    }

    @PutMapping("/{idProduto}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<ProdutoDTO> atualizar(@PathVariable Long idRestaurante, @PathVariable Long idProduto, @RequestBody @Valid ProdutoInput input) {
        try {
            Produto produtoAtual = service.buscar(idProduto, idRestaurante);
            converter.copyToDomainObject(input, produtoAtual);
            return ResponseEntity.ok(converter.toDTO(service.salvar(produtoAtual)));
        } catch (RestauranteNaoEncontradoException | ProdutoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage());
        }

    }

    @DeleteMapping("/{idProduto}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociarProduto(@PathVariable Long idRestaurante, @PathVariable Long idProduto) {
        try {
            this.service.remover(idProduto, idRestaurante);
        } catch (RestauranteNaoEncontradoException | ProdutoNaoEncontradoException e) {
            throw new NegocioException(e.getMessage());
        }

    }


}
