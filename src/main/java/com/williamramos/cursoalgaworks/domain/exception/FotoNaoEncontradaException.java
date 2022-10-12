package com.williamramos.cursoalgaworks.domain.exception;

public class FotoNaoEncontradaException extends EntidadeNaoEncontradaException {
    public FotoNaoEncontradaException() {
        super(String.format("Foto não encontrado"));
    }

    public FotoNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public FotoNaoEncontradaException(Long id) {
        this(String.format("Foto  %d, não encontrado", id));
    }
}
