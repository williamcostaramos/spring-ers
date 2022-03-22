package com.williamramos.cursoalgaworks.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "tb_cidade")
public class Cidade extends BaseEntity{

    @Column(name = "nome")
    private String nome;
    @ManyToOne()
    @JoinColumn(name = "id_estado")
    private Estado estado;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
