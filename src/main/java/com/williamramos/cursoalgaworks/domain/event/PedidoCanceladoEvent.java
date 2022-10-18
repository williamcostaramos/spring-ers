package com.williamramos.cursoalgaworks.domain.event;

import com.williamramos.cursoalgaworks.domain.model.Pedido;

public class PedidoCanceladoEvent {
    private Pedido pedido;

    public PedidoCanceladoEvent(Pedido pedido) {
        this.pedido = pedido;
    }

    public Pedido getPedido() {
        return pedido;
    }
}
