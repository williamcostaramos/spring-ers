package com.williamramos.cursoalgaworks.domain.model;

import com.williamramos.cursoalgaworks.domain.model.enums.TipoUsuario;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_usuario")
public class Usuario extends BaseEntity {
    @Column(name = "nome")
    private String nome;

    @Column(name = "senha")
    private String senha;


    @Column(name = "email")
    private String email;

    @Column(name = "tipo")
    private TipoUsuario tipoUsuario;

    @Column(name = "ativo")
    private Boolean ativo = Boolean.TRUE;

    @ManyToMany
    @JoinTable(name = "tb_usuario_grupo",
            joinColumns = @JoinColumn(name = "id_tb_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_tb_grupo"))
    private Set<Grupo> grupos;


    public Usuario() {
        this.grupos = new HashSet<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Set<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(Set<Grupo> grupos) {
        this.grupos = grupos;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public void ativar(){
        this.ativo = true;
    }

    public void inativar(){
        this.ativo = false;
    }

    public boolean senhaCoincideCom(String senha){
        return this.senha.equals(senha);
    }
    public boolean senhaNaoCoincideCom(String senha){
        return !this.senha.equals(senha);
    }

    public boolean adicionarGrupo(Grupo grupo){
        return this.grupos.add(grupo);
    }
    public boolean removerGrupo(Grupo grupo){
        return this.grupos.remove(grupo);
    }
}
