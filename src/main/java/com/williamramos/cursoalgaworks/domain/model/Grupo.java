package com.williamramos.cursoalgaworks.domain.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_grupo")
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Grupo grupo = (Grupo) o;

        return getId().equals(grupo.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}