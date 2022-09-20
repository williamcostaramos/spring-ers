package com.williamramos.cursoalgaworks.domain.exception;

public class FormaPagamentoNaoEncontradaException extends EntidadeNaoEncontradaException{
    public FormaPagamentoNaoEncontradaException() {
        super(String.format("Cozinha não encontrado"));
    }

    public FormaPagamentoNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public FormaPagamentoNaoEncontradaException(Long id) {
        this(String.format("Forma de Pagamento  %d, não encontrado", id));
    }
}
