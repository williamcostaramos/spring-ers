package com.williamramos.cursoalgaworks.domain.exception;

public class PedidoEmUsoException extends EntidadeEmUsoException{
    public PedidoEmUsoException(String mensagem) {
        super(mensagem);
    }

    public PedidoEmUsoException(Long id) {
        super(String.format("Pedido %d não pode ser removida, pois está em uso",id));
    }
}
