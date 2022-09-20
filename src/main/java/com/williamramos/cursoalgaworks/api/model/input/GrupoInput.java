package com.williamramos.cursoalgaworks.api.model.input;

import javax.validation.constraints.NotBlank;

public class GrupoInput {
    @NotBlank
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
