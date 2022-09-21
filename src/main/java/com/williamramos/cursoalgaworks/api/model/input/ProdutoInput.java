package com.williamramos.cursoalgaworks.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public class ProdutoInput {
    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;
    @PositiveOrZero
    @NotNull
    private BigDecimal preco;
    @NotNull
    private Boolean ativo;
    @Valid
    @NotNull
    private RestauranteIdInput restaurante;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public RestauranteIdInput getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(RestauranteIdInput restaurante) {
        this.restaurante = restaurante;
    }
}
