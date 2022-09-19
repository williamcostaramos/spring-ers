package com.williamramos.cursoalgaworks.domain.exception;

public class UsuarioNaoEncontradoException extends EntidadeNaoEncontradaException{
    public UsuarioNaoEncontradoException() {
        super(String.format("Cozinha não encontrado"));
    }

    public UsuarioNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public UsuarioNaoEncontradoException(Long id) {
        this(String.format("Usuario %d, não encontrado", id));
    }
}
