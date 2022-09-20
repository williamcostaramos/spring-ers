package com.williamramos.cursoalgaworks.domain.exception;

public class GrupoNaoEncontradoException extends EntidadeNaoEncontradaException{
    public GrupoNaoEncontradoException() {
        super(String.format("Cozinha não encontrado"));
    }

    public GrupoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public GrupoNaoEncontradoException(Long id) {
        this(String.format("Grupo %d, não encontrado", id));
    }
}
