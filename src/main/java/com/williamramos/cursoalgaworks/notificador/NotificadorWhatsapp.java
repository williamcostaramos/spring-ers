package com.williamramos.cursoalgaworks.notificador;

import com.williamramos.cursoalgaworks.domain.model.Cliente;
import org.springframework.stereotype.Component;

@Component
public class NotificadorWhatsapp implements Notificador {

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        System.out.printf("Notificando: %s via Whatsapp: %s : %s", cliente.getNome(), cliente.getTelefone(), mensagem);
    }


}
