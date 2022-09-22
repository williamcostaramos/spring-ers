package com.williamramos.cursoalgaworks.api.model.input;

import com.williamramos.cursoalgaworks.domain.model.enums.StatusPedido;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class PedidoInput {

    private BigDecimal subtotal;

    private BigDecimal taxaFrete;

    private LocalDateTime dataCriacao =LocalDateTime.now();

    private BigDecimal valorTotal;

    private LocalDateTime dataConfirmacao;

    private LocalDateTime dataCancelamento;

    private LocalDateTime dataEntrega;
    @NotNull
    @Valid
    private FormaPagamentoIdInput formaPagamento;

    @NotNull
    @Valid
    private RestauranteIdInput restaurante;

    @NotNull
    @Valid
    private UsuarioIdInput cliente;

    @NotNull
    private EnderecoInput endereco;

    @Valid
    @Size(min = 1)
    @NotNull
    private List<ItemPedidoInput> itens;

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTaxaFrete() {
        return taxaFrete;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
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
    

    public FormaPagamentoIdInput getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamentoIdInput formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public RestauranteIdInput getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(RestauranteIdInput restaurante) {
        this.restaurante = restaurante;
    }

    public UsuarioIdInput getCliente() {
        return cliente;
    }

    public void setCliente(UsuarioIdInput cliente) {
        this.cliente = cliente;
    }

    public EnderecoInput getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoInput endereco) {
        this.endereco = endereco;
    }

    public List<ItemPedidoInput> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoInput> itens) {
        this.itens = itens;
    }
}
