package com.williamramos.cursoalgaworks.domain.event;

import com.williamramos.cursoalgaworks.domain.model.Pedido;

public class PedidoEntregueEvent {
    private Pedido pedido;

    public PedidoEntregueEvent(Pedido pedido) {
        this.pedido = pedido;
    }

    public Pedido getPedido() {
        return pedido;
    }
}
