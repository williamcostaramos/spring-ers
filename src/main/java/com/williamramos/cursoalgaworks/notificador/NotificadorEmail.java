package com.williamramos.cursoalgaworks.notificador;

import com.williamramos.cursoalgaworks.domain.model.Cliente;
import com.williamramos.cursoalgaworks.properties.NotificadorProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@TipoNotificador(NivelUrgencia.NORMAL)
public class NotificadorEmail implements Notificador {

    @Autowired
    private NotificadorProperties properties;

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        System.out.printf("Notificando: %s via email: %s : %s \n", cliente.getNome(), cliente.getEmail(), mensagem);
        System.out.println("Servidor: "+ properties.getServidor());
        System.out.println("Servidor: "+ properties.getPorta());
    }

}
