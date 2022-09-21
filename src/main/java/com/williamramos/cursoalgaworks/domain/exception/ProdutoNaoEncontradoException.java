package com.williamramos.cursoalgaworks.domain.exception;

public class ProdutoNaoEncontradoException extends EntidadeNaoEncontradaException{
    public ProdutoNaoEncontradoException() {
        super(String.format("Produto não encontrado"));
    }

    public ProdutoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public ProdutoNaoEncontradoException(Long idProduto, Long idRestaurante) {
        this(String.format("Produto  %d, não encontrado para o restaurante %d", idProduto, idRestaurante));
    }
    public ProdutoNaoEncontradoException(Long idProduto) {
        this(String.format("Produto  %d, não encontrado", idProduto));
    }
}
