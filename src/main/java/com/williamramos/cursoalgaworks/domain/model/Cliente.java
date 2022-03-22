package com.williamramos.cursoalgaworks.domain.model;

public class Cliente {
    private String nome;
    private String telefone;
    private String email;
    private boolean ativo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void ativar(){
        this.ativo = true;
    }
}
