package com.williamramos.cursoalgaworks.domain.listener;

import com.williamramos.cursoalgaworks.domain.event.PedidoCanceladoEvent;
import com.williamramos.cursoalgaworks.domain.model.Pedido;
import com.williamramos.cursoalgaworks.service.EmailSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class NotificacaoPedidoCanceladoListener {
    @Autowired
    private EmailSendService emailSendService;

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void aoCancelarPedido(PedidoCanceladoEvent event) {
        Pedido pedido = event.getPedido();
        EmailSendService.Mensagem mensagem = new EmailSendService.Mensagem();
        mensagem.setAssunto(String.format("%s - Pedido Cancelado ", pedido.getRestaurante().getNome()));
        mensagem.setMensagem("pedido-cancelado.html");
        mensagem.variavel("pedido", pedido);
        mensagem.destinatario(pedido.getCliente().getEmail());
        emailSendService.enviar(mensagem);

    }
}
