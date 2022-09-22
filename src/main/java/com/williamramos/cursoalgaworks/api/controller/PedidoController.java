package com.williamramos.cursoalgaworks.api.controller;

import com.williamramos.cursoalgaworks.api.converter.Converter;
import com.williamramos.cursoalgaworks.api.model.dto.PedidoDTO;
import com.williamramos.cursoalgaworks.api.model.dto.PedidoResumoDTO;
import com.williamramos.cursoalgaworks.api.model.input.PedidoInput;
import com.williamramos.cursoalgaworks.domain.exception.*;
import com.williamramos.cursoalgaworks.domain.model.Pedido;
import com.williamramos.cursoalgaworks.domain.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService service;


    @Autowired
    private Converter<Pedido, PedidoDTO> converter;
    @Autowired
    private Converter<Pedido, PedidoResumoDTO> converterResumido;



    @GetMapping()
    public ResponseEntity<List<PedidoResumoDTO>> listar() {
        List<PedidoResumoDTO> Pedidos = converterResumido.toDTOList(service.listar());
        return ResponseEntity.status(HttpStatus.OK).body(Pedidos);
    }

    @GetMapping("/{id}")
    public PedidoDTO buscar(@PathVariable Long id) throws IllegalAccessException {

        return converter.toDTO(service.buscar(id));
    }



    @PostMapping()
    public ResponseEntity<PedidoDTO> salvar(@RequestBody @Valid PedidoInput PedidoInput) {
        try {
            Pedido Pedido = converter.inputToDomain(PedidoInput);
            PedidoDTO rest = converter.toDTO(service.salvar(Pedido));
            return ResponseEntity.ok(rest);
        } catch (RestauranteNaoEncontradoException | FormaPagamentoNaoEncontradaException | UsuarioNaoEncontradoException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> salvar(@PathVariable Long id, @RequestBody @Valid PedidoInput Pedido) {
        try {
            Pedido PedidoAtual = service.buscar(id);
            converter.copyToDomainObject(Pedido, PedidoAtual);
            PedidoAtual = service.salvar(PedidoAtual);
            return ResponseEntity.ok(converter.toDTO(PedidoAtual));

        } catch (RestauranteNaoEncontradoException | FormaPagamentoNaoEncontradaException | UsuarioNaoEncontradoException e) {
            throw new NegocioException(e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {
        try {
            service.remover(id);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException e) {
            throw new PedidoNaoEncontradoException(id);
        } catch (EntidadeEmUsoException e) {
            throw new PedidoEmUsoException(id);
        }
    }

}
