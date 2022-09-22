package com.williamramos.cursoalgaworks.api.controller;

import com.williamramos.cursoalgaworks.api.converter.Converter;
import com.williamramos.cursoalgaworks.api.model.dto.FormaPagamentoDTO;
import com.williamramos.cursoalgaworks.domain.exception.RestauranteNaoEncontradoException;
import com.williamramos.cursoalgaworks.domain.exception.NegocioException;
import com.williamramos.cursoalgaworks.domain.exception.FormaPagamentoNaoEncontradaException;
import com.williamramos.cursoalgaworks.domain.model.FormaPagamento;
import com.williamramos.cursoalgaworks.domain.model.Restaurante;
import com.williamramos.cursoalgaworks.domain.service.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes/{idRestaurante}/formas-pagamento")
public class RestauranteFormaPagamentoController {
    @Autowired
    private RestauranteService service;
    @Autowired
    private Converter<FormaPagamento, FormaPagamentoDTO> converter;

    @GetMapping()
    public ResponseEntity<List<FormaPagamentoDTO>> listar(@PathVariable Long idRestaurante) {
        Restaurante Restaurante = service.buscar(idRestaurante);
        return ResponseEntity.ok(converter.toDTOList(Restaurante.getFormaPagamentos()));
    }



    @PutMapping("/{idFormaPagamento}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associarFormaPagamento(@PathVariable Long idRestaurante, @PathVariable Long idFormaPagamento) {
        try {
            service.associarFormaPagamento(idRestaurante, idFormaPagamento);
        } catch (RestauranteNaoEncontradoException | FormaPagamentoNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }

    }

    @DeleteMapping("/{idFormaPagamento}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociarFormaPagamento(@PathVariable Long idRestaurante, @PathVariable Long idFormaPagamento) {
        try {
            this.service.desassociarFormaPagamento(idRestaurante, idFormaPagamento);
        } catch (RestauranteNaoEncontradoException | FormaPagamentoNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }

    }


}
