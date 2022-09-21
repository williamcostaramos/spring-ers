package com.williamramos.cursoalgaworks.domain.exception;

public class ProdutoEmUsoException extends EntidadeEmUsoException{
    public ProdutoEmUsoException(String mensagem) {
        super(mensagem);
    }

    public ProdutoEmUsoException(Long id) {
        super(String.format("Produto %d não pode ser removida, pois está em uso",id));
    }
}
