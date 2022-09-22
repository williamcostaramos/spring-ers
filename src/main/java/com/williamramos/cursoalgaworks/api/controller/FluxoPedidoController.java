package com.williamramos.cursoalgaworks.api.controller;

import com.williamramos.cursoalgaworks.domain.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos/{idPedido}")
public class FluxoPedidoController {
    @Autowired
    private PedidoService service;

    @PutMapping("/confirmacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confimar(@PathVariable Long idPedido){
        service.confirmacaoPedido(idPedido);
    }
    @PutMapping("/entrega")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void entregar(@PathVariable Long idPedido){
        service.entregarPedido(idPedido);
    }
    @PutMapping("/cancelamento")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelar(@PathVariable Long idPedido){
        service.cancelamentoPedido(idPedido);
    }
}
