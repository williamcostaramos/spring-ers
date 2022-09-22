package com.williamramos.cursoalgaworks.domain.model;

import javax.persistence.*;
import java.math.BigDecimal;
@Entity
@Table(name = "tb_item_pedido")
public class ItemPedido extends BaseEntity{

    @Column(name = "quantidade")
    private Integer quantidade;
    @Column(name = "preco_unitario")
    private BigDecimal precoUnitario;
    @Column(name = "preco_total")
    private BigDecimal precoTotal;
    @Column(name = "observacao")
    private String observacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public BigDecimal getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(BigDecimal precoTotal) {
        this.precoTotal = precoTotal;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void calcularPrecoTotal(){

        if(this.precoUnitario == null){
            this.precoUnitario =BigDecimal.ZERO;
        }
        if(this.quantidade ==null){
            this.quantidade =0;
        }
        this.setPrecoTotal(this.precoUnitario.multiply(new BigDecimal(this.quantidade)));
    }

}
