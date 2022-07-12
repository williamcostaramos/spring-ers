package com.williamramos.cursoalgaworks.domain.exception;

public class CozinhaEmUsoException extends EntidadeEmUsoException{
    public CozinhaEmUsoException(String mensagem) {
        super(mensagem);
    }

    public CozinhaEmUsoException(Long id) {
        super(String.format("Cozinha de codigo %d não pode ser removida, pois está em uso",id));
    }
}
