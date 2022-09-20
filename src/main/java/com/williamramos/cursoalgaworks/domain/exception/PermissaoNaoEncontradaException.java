package com.williamramos.cursoalgaworks.domain.exception;

public class PermissaoNaoEncontradaException extends EntidadeNaoEncontradaException{
    public PermissaoNaoEncontradaException() {
        super(String.format("Cozinha não encontrado"));
    }

    public PermissaoNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public PermissaoNaoEncontradaException(Long id) {
        this(String.format("Permissão de codigo  %d, não encontrado", id));
    }
}
