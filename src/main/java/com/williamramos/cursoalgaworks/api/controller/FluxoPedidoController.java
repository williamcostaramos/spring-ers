package com.williamramos.cursoalgaworks.api.controller;

import com.williamramos.cursoalgaworks.domain.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos/{codigo}")
public class FluxoPedidoController {
    @Autowired
    private PedidoService service;

    @PutMapping("/confirmacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confimar(@PathVariable String codigo){
        service.confirmacaoPedido(codigo);
    }
    @PutMapping("/entrega")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void entregar(@PathVariable String codigo){
        service.entregarPedido(codigo);
    }
    @PutMapping("/cancelamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelar(@PathVariable String codigo){
        service.cancelamentoPedido(codigo);
    }
}
