package com.williamramos.cursoalgaworks.api.model.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FotoProdutoDTO {
    private String nomeArquivo;
    private String descricao;
    private String contentType;
    private Double tamanho;

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Double getTamanho() {
        return tamanho;
    }

    public void setTamanho(Double tamanho) {

        this.tamanho= BigDecimal.valueOf(tamanho / (1024 * 1024)).setScale(2, RoundingMode.HALF_UP).doubleValue();


    }
}
