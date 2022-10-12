package com.williamramos.cursoalgaworks.domain.exception;

public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException{
    public PedidoNaoEncontradoException() {
        super(String.format("Pedido não encontrado"));
    }

    public PedidoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public PedidoNaoEncontradoException(Long idProduto, Long idRestaurante) {
        this(String.format("Pedido  %d, não encontrado para o restaurante %d", idProduto, idRestaurante));
    }
    public PedidoNaoEncontradoException(Long idProduto) {
        this(String.format("Pedido  %d, não encontrado", idProduto));
    }
}
