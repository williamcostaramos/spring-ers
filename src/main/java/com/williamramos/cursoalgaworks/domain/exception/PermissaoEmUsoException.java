package com.williamramos.cursoalgaworks.domain.exception;

public class PermissaoEmUsoException extends EntidadeEmUsoException{
    public PermissaoEmUsoException(String mensagem) {
        super(mensagem);
    }

    public PermissaoEmUsoException(Long id) {
        super(String.format("Permissao %d não pode ser removida, pois está em uso",id));
    }
}
