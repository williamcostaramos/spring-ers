package com.williamramos.cursoalgaworks.service;

import com.williamramos.cursoalgaworks.domain.model.Cliente;
import com.williamramos.cursoalgaworks.notificador.NivelUrgencia;
import com.williamramos.cursoalgaworks.notificador.Notificador;
import com.williamramos.cursoalgaworks.notificador.TipoNotificador;
import com.williamramos.cursoalgaworks.observable.ClienteAtivoEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AtivacaoClienteService {
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public void ativar(Cliente cliente) {
        //TODO ativação do cliente
        cliente.ativar();
        eventPublisher.publishEvent(new ClienteAtivoEvent(cliente));
    }
}
