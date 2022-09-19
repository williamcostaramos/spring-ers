package com.williamramos.cursoalgaworks.api.model.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class RestauranteInput {
    @NotBlank()
    private String nome;
    private BigDecimal taxaFrete;

    private CozinhaIdInput cozinha;

    @Valid
    @NotNull
    private EnderecoInput endereco;

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

    public CozinhaIdInput getCozinha() {
        return cozinha;
    }

    public void setCozinha(CozinhaIdInput cozinha) {
        this.cozinha = cozinha;
    }

    public EnderecoInput getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoInput endereco) {
        this.endereco = endereco;
    }
}
