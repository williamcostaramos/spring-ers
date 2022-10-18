package com.williamramos.cursoalgaworks.domain.listener;

import com.williamramos.cursoalgaworks.domain.event.PedidoConfirmadoEvent;
import com.williamramos.cursoalgaworks.domain.model.Pedido;
import com.williamramos.cursoalgaworks.service.EmailSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class NotificacaoPedidoConfirmadoListener {
    @Autowired
    private EmailSendService emailSendService;

    @TransactionalEventListener
    public void aoConfirmarPedido(PedidoConfirmadoEvent event) {
        Pedido pedido = event.getPedido();
        EmailSendService.Mensagem mensagem = new EmailSendService.Mensagem();
        mensagem.setAssunto(String.format("%s - Pedido Confirmado ", pedido.getRestaurante().getNome()));
        mensagem.setMensagem("pedido-confirmado.html");
        mensagem.variavel("pedido", pedido);
        mensagem.destinatario(pedido.getCliente().getEmail());
        emailSendService.enviar(mensagem);

    }
}
