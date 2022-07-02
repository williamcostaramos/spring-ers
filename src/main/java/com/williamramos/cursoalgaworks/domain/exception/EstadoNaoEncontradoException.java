package com.williamramos.cursoalgaworks.domain.exception;

public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException{

    public EstadoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public EstadoNaoEncontradoException(Long id) {
        this(String.format("Estado de codigo %d, não encontrada ", id));
    }
}
