package com.williamramos.cursoalgaworks.observable;

import com.williamramos.cursoalgaworks.domain.model.Cliente;

public class NotafiscalEmitidaEvent {
    private Cliente cliente;

    public NotafiscalEmitidaEvent(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }
}
