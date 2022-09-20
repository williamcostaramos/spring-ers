package com.williamramos.cursoalgaworks.domain.exception;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException{
    public RestauranteNaoEncontradoException() {
        super(String.format("Restaurante não encontrado"));
    }

    public RestauranteNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public RestauranteNaoEncontradoException(Long id) {
        this(String.format("Restaurante de codigo %d, não encontrado", id));
    }
}
