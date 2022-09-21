package com.williamramos.cursoalgaworks.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CidadeInput {
    @NotBlank
    private String nome;
    @NotNull
    @Valid
    private EstadoIdInput estado;
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public EstadoIdInput getEstado() {
        return estado;
    }

    public void setEstado(EstadoIdInput estado) {
        this.estado = estado;
    }
}
