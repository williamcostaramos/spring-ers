package com.williamramos.cursoalgaworks.domain.model;

import com.williamramos.cursoalgaworks.domain.model.enums.TipoUsuario;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_user")
public class Usuario extends BaseEntity {
    @Column(name = "nome")
    private String nome;
    @Column(name = "senha")
    private String senha;
    @Column(name = "tipo")
    private TipoUsuario tipoUsuario;

    @ManyToMany
    @JoinTable(name = "tb_usuario_grupo",
            joinColumns = @JoinColumn(name = "id_tb_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_tb_grupo"))
    private List<Grupo> grupos = new ArrayList<>();


    public Usuario() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }
}
