package com.williamramos.cursoalgaworks.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "tb_grupo")
public class Grupo extends BaseEntity {
    @Column(name = "nome")
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}