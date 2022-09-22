package com.williamramos.cursoalgaworks.api.model.dto;

import com.williamramos.cursoalgaworks.domain.model.enums.StatusPedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class PedidoResumoDTO {
    private String codigo;
    private BigDecimal subtotal;
    private BigDecimal valorTotal;
    private BigDecimal taxaFrete;
    private LocalDateTime dataCriacao;
    private StatusPedido statusPedido;
    private RestauranteResumoDTO restaurante;
    private UsuarioResumoDTO cliente;

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
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

    public StatusPedido getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(StatusPedido statusPedido) {
        this.statusPedido = statusPedido;
    }

    public RestauranteResumoDTO getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(RestauranteResumoDTO restaurante) {
        this.restaurante = restaurante;
    }

    public UsuarioResumoDTO getCliente() {
        return cliente;
    }

    public void setCliente(UsuarioResumoDTO cliente) {
        this.cliente = cliente;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
