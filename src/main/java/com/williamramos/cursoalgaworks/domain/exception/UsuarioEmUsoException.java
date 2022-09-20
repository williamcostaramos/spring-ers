package com.williamramos.cursoalgaworks.domain.exception;

public class UsuarioEmUsoException extends EntidadeEmUsoException{
    public UsuarioEmUsoException(String mensagem) {
        super(mensagem);
    }

    public UsuarioEmUsoException(Long id) {
        super(String.format("Usuario %d não pode ser removida, pois está em uso",id));
    }
}
