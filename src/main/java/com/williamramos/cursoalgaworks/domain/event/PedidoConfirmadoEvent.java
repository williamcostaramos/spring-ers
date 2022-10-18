package com.williamramos.cursoalgaworks.domain.event;

import com.williamramos.cursoalgaworks.domain.model.Pedido;

public class PedidoConfirmadoEvent {
    private Pedido pedido;

    public PedidoConfirmadoEvent(Pedido pedido) {
        this.pedido = pedido;
    }

    public Pedido getPedido() {
        return pedido;
    }
}
