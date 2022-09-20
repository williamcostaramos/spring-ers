package com.williamramos.cursoalgaworks.api.exception;

public enum TypeProblem {
    RECURSO_NAO_ENCONTRADO("/nao-encontrado", "Recurso não encontrado"),
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
    MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "O corpo da requisição não e valido. Por favor verifique a Sintaxe"),
    PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido"),
    DADOS_INVALIDO("/parametro-invalido", "Dados Inválidos"),

    ERRO_DE_SISTEMA("/erro-de-sistema", "Erro interno do sistema");


    private final String url;
    private final String descricao;

    TypeProblem(String path, String descricao) {
        this.url = "127.0.0.1:8081/api"+path;
        this.descricao = descricao;
    }

    public String getUrl() {
        return url;
    }

    public String getDescricao() {
        return descricao;
    }
}
