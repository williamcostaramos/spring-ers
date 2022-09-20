package com.williamramos.cursoalgaworks.domain.exception;

public class CidadeEmUsoException extends EntidadeEmUsoException{
    public CidadeEmUsoException(String mensagem) {
        super(mensagem);
    }

    public CidadeEmUsoException(Long id) {
        super(String.format("Cidade de código %d não pode ser removido, pois está em uso",id));
    }
}
