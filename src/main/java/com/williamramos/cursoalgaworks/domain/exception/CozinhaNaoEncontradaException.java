package com.williamramos.cursoalgaworks.domain.exception;

public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException{
    public CozinhaNaoEncontradaException() {
        super(String.format("Cozinha não encontrado"));
    }

    public CozinhaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public CozinhaNaoEncontradaException(Long id) {
        this(String.format("Cozinha de codigo %d, não encontrado", id));
    }
}
