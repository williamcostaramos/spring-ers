package com.williamramos.cursoalgaworks.listener;

import com.williamramos.cursoalgaworks.notificador.NivelUrgencia;
import com.williamramos.cursoalgaworks.notificador.Notificador;
import com.williamramos.cursoalgaworks.notificador.TipoNotificador;
import com.williamramos.cursoalgaworks.observable.ClienteAtivoEvent;
import com.williamramos.cursoalgaworks.observable.NotafiscalEmitidaEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoListener {
    @TipoNotificador(NivelUrgencia.NORMAL)
    @Autowired
    private Notificador notificador;

    @EventListener
    public void clienteAtivoEvent(ClienteAtivoEvent event){
        this.notificador.notificar(event.getCliente(),"Cliente ativado com sucesso");
    }
    @EventListener
    public void notaFiscalEmitidaEvent(NotafiscalEmitidaEvent event){
        this.notificador.notificar(event.getCliente(), "Nota fiscal emitida");
    }
}
