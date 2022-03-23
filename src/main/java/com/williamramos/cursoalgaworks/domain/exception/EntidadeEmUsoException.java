package com.williamramos.cursoalgaworks.domain.exception;

public class EntidadeEmUsoException extends RuntimeException{
    public EntidadeEmUsoException(String mensagem) {
        super(mensagem);
    }
}
