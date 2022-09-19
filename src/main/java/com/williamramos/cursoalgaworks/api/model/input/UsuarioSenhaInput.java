package com.williamramos.cursoalgaworks.api.model.input;

import com.williamramos.cursoalgaworks.domain.model.enums.TipoUsuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UsuarioSenhaInput extends UsuarioInput {
    @NotBlank
    private String senha;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
