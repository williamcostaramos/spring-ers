package com.williamramos.cursoalgaworks.domain.model;

import com.williamramos.cursoalgaworks.domain.event.PedidoCanceladoEvent;
import com.williamramos.cursoalgaworks.domain.event.PedidoConfirmadoEvent;
import com.williamramos.cursoalgaworks.domain.event.PedidoEntregueEvent;
import com.williamramos.cursoalgaworks.domain.model.enums.StatusPedido;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_pedido")
public class Pedido extends AbstractAggregateRoot<Pedido> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo")
    private String codigo;
    @Column(name = "subtotal")
    private BigDecimal subtotal;

    @Column(name = "valor_total")
    private BigDecimal valorTotal;
    @Column(name = "taxa_frete")
    private BigDecimal taxaFrete;
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;
    @Column(name = "data_confirmacao")
    private LocalDateTime dataConfirmacao;
    @Column(name = "data_cancelamento")
    private LocalDateTime dataCancelamento;
    @Column(name = "data_entrega")
    private LocalDateTime dataEntrega;

    @Embedded
    private Endereco endereco;
    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private StatusPedido statusPedido = StatusPedido.CRIADO;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "forma_pagamento_id")
    private FormaPagamento formaPagamento;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurante_id")
    private Restaurante restaurante;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_cliente_id")
    private Usuario cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itensPedido = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTaxaFrete() {
        return taxaFrete;
    }

    public void setTaxaFrete(BigDecimal taxaFrete) {
        this.taxaFrete = taxaFrete;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataConfirmacao() {
        return dataConfirmacao;
    }

    public void setDataConfirmacao(LocalDateTime dataConfirmacao) {
        this.dataConfirmacao = dataConfirmacao;
    }

    public LocalDateTime getDataCancelamento() {
        return dataCancelamento;
    }

    public void setDataCancelamento(LocalDateTime dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }

    public LocalDateTime getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDateTime dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public StatusPedido getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(StatusPedido statusPedido) {
        this.statusPedido = statusPedido;
    }

    public List<ItemPedido> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<ItemPedido> itensPedido) {
        this.itensPedido = itensPedido;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Pedido pedido = (Pedido) o;

        return getId().equals(pedido.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void calcularValorTotal() {
        this.itensPedido.forEach(ItemPedido::calcularPrecoTotal);
        this.subtotal = itensPedido.stream().map(itemPedido -> itemPedido.getPrecoTotal()).reduce(BigDecimal.ZERO, BigDecimal::add);
        this.valorTotal = this.subtotal.add(this.taxaFrete);
    }

    public void definirValorFrete() {
        setTaxaFrete(this.restaurante.getTaxaFrete());
    }

    public void atribuirPedidoAosItens() {
        this.itensPedido.forEach(itemPedido -> itemPedido.setPedido(this));
    }

    @PrePersist
    private void gerarCodigo() {
        setCodigo(UUID.randomUUID().toString());
    }

    public void confirmar() {
        this.setStatusPedido(StatusPedido.CONFIRMADO);
        this.setDataConfirmacao(LocalDateTime.now());
        registerEvent(new PedidoConfirmadoEvent(this));
    }

    public void cancelar() {
        this.setStatusPedido(StatusPedido.CANCELADO);
        this.setDataCancelamento(LocalDateTime.now());
        registerEvent(new PedidoCanceladoEvent(this));
    }
    public void entregar() {
        this.setStatusPedido(StatusPedido.CANCELADO);
        this.setDataEntrega(LocalDateTime.now());
        registerEvent(new PedidoEntregueEvent(this));
    }

}
