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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("/{codigo}")
    public PedidoDTO buscar(@PathVariable String codigo) {
        return converter.toDTO(service.buscar(codigo));
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

    @PutMapping("/{codigo}")
    public ResponseEntity<?> salvar(@PathVariable String codigo, @RequestBody @Valid PedidoInput Pedido) {
        try {
            Pedido PedidoAtual = service.buscar(codigo);
            converter.copyToDomainObject(Pedido, PedidoAtual);
            PedidoAtual = service.salvar(PedidoAtual);
            return ResponseEntity.ok(converter.toDTO(PedidoAtual));

        } catch (RestauranteNaoEncontradoException | FormaPagamentoNaoEncontradaException | UsuarioNaoEncontradoException e) {
            throw new NegocioException(e.getMessage());
        }

    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable String codigo) {
        try {
            service.remover(codigo);
        } catch (EntidadeNaoEncontradaException e) {
            throw new PedidoNaoEncontradoException(codigo);
        } catch (EntidadeEmUsoException e) {
            throw new PedidoEmUsoException(codigo);
        }
    }

}
