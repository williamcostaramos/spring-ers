package com.williamramos.cursoalgaworks.domain.exception;

public class EstadoEmUsoException extends EntidadeEmUsoException{
    public EstadoEmUsoException(String mensagem) {
        super(mensagem);
    }

    public EstadoEmUsoException(Long id) {
        super(String.format("Estado de código %d não pode ser removido, pois está em uso",id));
    }
}
