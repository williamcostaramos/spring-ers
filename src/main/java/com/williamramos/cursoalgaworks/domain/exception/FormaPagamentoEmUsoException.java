package com.williamramos.cursoalgaworks.domain.exception;

public class FormaPagamentoEmUsoException extends EntidadeEmUsoException{
    public FormaPagamentoEmUsoException(String mensagem) {
        super(mensagem);
    }

    public FormaPagamentoEmUsoException(Long id) {
        super(String.format("Forma Pagamento %d não pode ser removida, pois está em uso",id));
    }
}
