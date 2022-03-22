package com.williamramos.cursoalgaworks.observable;

import com.williamramos.cursoalgaworks.domain.model.Cliente;

public class ClienteAtivoEvent {
    private Cliente cliente;

    public ClienteAtivoEvent(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }
}
