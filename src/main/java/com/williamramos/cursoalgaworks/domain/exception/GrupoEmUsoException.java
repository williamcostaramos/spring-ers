package com.williamramos.cursoalgaworks.domain.exception;

public class GrupoEmUsoException extends EntidadeEmUsoException{
    public GrupoEmUsoException(String mensagem) {
        super(mensagem);
    }

    public GrupoEmUsoException(Long id) {
        super(String.format("Grupo %d não pode ser removida, pois está em uso",id));
    }
}
