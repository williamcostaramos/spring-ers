package com.williamramos.cursoalgaworks.api.model.input;

import javax.validation.constraints.NotBlank;

public class SenhaInput extends UsuarioInput {
    @NotBlank
    private String senhaAtual;
    @NotBlank
    private String senhaNova;

    public String getSenhaAtual() {
        return senhaAtual;
    }

    public void setSenhaAtual(String senhaAtual) {
        this.senhaAtual = senhaAtual;
    }

    public String getSenhaNova() {
        return senhaNova;
    }

    public void setSenhaNova(String senhaNova) {
        this.senhaNova = senhaNova;
    }
}
