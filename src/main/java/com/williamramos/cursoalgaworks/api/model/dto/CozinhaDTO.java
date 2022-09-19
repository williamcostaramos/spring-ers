package com.williamramos.cursoalgaworks.api.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class CozinhaDTO {

    private Long id;
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
