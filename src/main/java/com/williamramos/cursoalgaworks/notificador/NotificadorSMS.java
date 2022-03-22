package com.williamramos.cursoalgaworks.notificador;

import com.williamramos.cursoalgaworks.domain.model.Cliente;
import org.springframework.stereotype.Component;

@Component
@TipoNotificador(NivelUrgencia.URGENTE)
public class NotificadorSMS implements Notificador {

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        System.out.printf("Notificando: %s via SMS: %s : %s", cliente.getNome(), cliente.getTelefone(), mensagem);
    }


}
