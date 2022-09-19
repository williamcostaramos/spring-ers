package com.williamramos.cursoalgaworks.domain.exception;

public class RestauranteEmUsoException extends EntidadeEmUsoException{
    public RestauranteEmUsoException(String mensagem) {
        super(mensagem);
    }

    public RestauranteEmUsoException(Long id) {
        super(String.format("Restaurante de codigo %d não pode ser removido, pois está em uso",id));
    }
}
