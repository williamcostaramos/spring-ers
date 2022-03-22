package com.williamramos.cursoalgaworks.domain.model.enums;

public enum TipoUsuario {
    ROOT("root");

    TipoUsuario(String descricao) {
        this.descricao = descricao;
    }

    private String descricao;

}
