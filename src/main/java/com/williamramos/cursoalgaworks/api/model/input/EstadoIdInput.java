package com.williamramos.cursoalgaworks.api.model.input;

import org.springframework.lang.NonNull;

public class EstadoIdInput {
   @NonNull
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
