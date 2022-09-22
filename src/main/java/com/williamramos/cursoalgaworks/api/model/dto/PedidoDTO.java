package com.williamramos.cursoalgaworks.api.model.dto;

import com.williamramos.cursoalgaworks.domain.model.enums.StatusPedido;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class PedidoDTO extends PedidoResumoDTO{


    private LocalDateTime dataConfirmacao;

    private LocalDateTime dataCancelamento;
    private LocalDateTime dataEntrega;

    private FormaPagamentoDTO formaPagamento;


    private RestauranteResumoDTO restaurante;

    private UsuarioResumoDTO cliente;

    private EnderecoDTO endereco;


    private List<ItemPedidoDTO> itens;


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


    public FormaPagamentoDTO getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamentoDTO formaPagamento) {
        this.formaPagamento = formaPagamento;
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

    public List<ItemPedidoDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoDTO> itens) {
        this.itens = itens;
    }

    public EnderecoDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDTO endereco) {
        this.endereco = endereco;
    }
}
