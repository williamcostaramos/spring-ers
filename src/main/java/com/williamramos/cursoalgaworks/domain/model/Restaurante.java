package com.williamramos.cursoalgaworks.domain.model;

import javax.persistence.*;
import java.math.BigDecimal;
@Entity
@Table(name = "tb_restaurante")
public class Restaurante extends BaseEntity{
    @Column(name = "nome")
    private String nome;
    @Column(name = "taxa_frete")
    private BigDecimal taxaFrete;

    @ManyToOne()
    @JoinColumn(name = "cozinha_id")
    private Cozinha cozinha;

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

    public Cozinha getCozinha() {
        return cozinha;
    }

    public void setCozinha(Cozinha cozinha) {
        this.cozinha = cozinha;
    }
}
