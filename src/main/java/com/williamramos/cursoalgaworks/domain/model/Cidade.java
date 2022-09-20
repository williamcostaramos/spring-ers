package com.williamramos.cursoalgaworks.domain.model;

import com.williamramos.cursoalgaworks.core.validation.Groups;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

@Entity
@Table(name = "tb_cidade")
public class Cidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank()
    @Column(name = "nome")
    private String nome;

    @NotNull()
    @ConvertGroup(from =Default.class, to= Groups.EstadoId.class)
    @Valid
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
