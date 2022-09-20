package com.williamramos.cursoalgaworks.domain.model;

import com.williamramos.cursoalgaworks.core.validation.Groups;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_estado")
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = Groups.EstadoId.class)
    private Long id;

    @NotBlank(message = "nome do estado não pode ser vazio")
    @Column(name = "nome")
    private String nome;

    @NotBlank(message = "UF do estado não pode ser vazio")
    @Column(name = "uf")
    private String uf;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
