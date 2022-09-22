package com.williamramos.cursoalgaworks.api.model.dto;

import java.math.BigDecimal;

public class ItemPedidoDTO {
    private Long produtoId;
    private String produtoNome;
    private Integer quantidade;
    private BigDecimal preoUnitario;
    private BigDecimal precoTotal;

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public String getProdutoNome() {
        return produtoNome;
    }

    public void setProdutoNome(String produtoNome) {
        this.produtoNome = produtoNome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPreoUnitario() {
        return preoUnitario;
    }

    public void setPreoUnitario(BigDecimal preoUnitario) {
        this.preoUnitario = preoUnitario;
    }

    public BigDecimal getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(BigDecimal precoTotal) {
        this.precoTotal = precoTotal;
    }
}
