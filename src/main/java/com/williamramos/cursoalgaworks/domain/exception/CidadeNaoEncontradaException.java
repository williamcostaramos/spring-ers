package com.williamramos.cursoalgaworks.domain.exception;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException{
    public CidadeNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public CidadeNaoEncontradaException(Long id) {
        this(String.format("Cidade de codigo %d não encontrada", id));
    }

    public CidadeNaoEncontradaException() {
        super(String.format("Nenhuma cidade foi encontrada"));
    }
}
