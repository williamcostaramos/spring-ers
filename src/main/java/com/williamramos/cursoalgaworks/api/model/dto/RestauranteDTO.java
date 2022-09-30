package com.williamramos.cursoalgaworks.api.model.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonView;
import com.williamramos.cursoalgaworks.api.model.view.RestauranteView;

import java.math.BigDecimal;

public class RestauranteDTO {
    @JsonView(value={RestauranteView.Resumo.class, RestauranteView.ApenasNomeId.class})
    private Long id;
    @JsonView(value={RestauranteView.Resumo.class, RestauranteView.ApenasNomeId.class})
    private String nome;
    @JsonView(value={RestauranteView.Resumo.class})
    private BigDecimal taxaFrete;
    @JsonView(value={RestauranteView.Resumo.class})
    private Boolean ativo;
    @JsonView(value={RestauranteView.Resumo.class})
    private Boolean aberto;
    private EnderecoDTO endereco;


    private CozinhaDTO cozinha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getTaxaFrete() {
        return taxaFrete;
    }

    public void setTaxaFrete(BigDecimal taxaFrete) {
        this.taxaFrete = taxaFrete;
    }

    public CozinhaDTO getCozinha() {
        return cozinha;
    }

    public void setCozinha(CozinhaDTO cozinha) {
        this.cozinha = cozinha;
    }

    public EnderecoDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDTO endereco) {
        this.endereco = endereco;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Boolean getAberto() {
        return aberto;
    }

    public void setAberto(Boolean aberto) {
        this.aberto = aberto;
    }
}

