package com.williamramos.cursoalgaworks.api.model.input;

import javax.validation.constraints.NotNull;

public class FormaPagamentoIdInput {
    @NotNull
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
