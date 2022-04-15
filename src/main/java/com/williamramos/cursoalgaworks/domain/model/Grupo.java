package com.williamramos.cursoalgaworks.domain.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_grupo")
public class Grupo extends BaseEntity {
    @Column(name = "nome")
    private String nome;

    @ManyToMany
    @JoinTable(name = "tb_grupo_permissao",
            joinColumns = @JoinColumn(name = "id_tb_grupo"),
            inverseJoinColumns = @JoinColumn(name = "id_tb_permissao"))
    private List<Permissao> permissoes;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<Permissao> permissoes) {
        this.permissoes = permissoes;
    }
}