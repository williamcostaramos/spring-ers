package com.williamramos.cursoalgaworks.notificador;

import com.williamramos.cursoalgaworks.domain.model.Cliente;

public interface Notificador {
    void notificar(Cliente cliente, String mensagem);
}
