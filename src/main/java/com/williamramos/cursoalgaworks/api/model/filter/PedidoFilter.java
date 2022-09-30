package com.williamramos.cursoalgaworks.api.model.filter;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class PedidoFilter {
    private Long clienteId;
    private Long restauranteId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dataCriacaoInicio;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dataCriacaoFim;

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getRestauranteId() {
        return restauranteId;
    }

    public void setRestauranteId(Long restauranteId) {
        this.restauranteId = restauranteId;
    }

    public LocalDateTime getDataCriacaoInicio() {
        return dataCriacaoInicio;
    }

    public void setDataCriacaoInicio(LocalDateTime dataCriacaoInicio) {
        this.dataCriacaoInicio = dataCriacaoInicio;
    }

    public LocalDateTime getDataCriacaoFim() {
        return dataCriacaoFim;
    }

    public void setDataCriacaoFim(LocalDateTime dataCriacaoFim) {
        this.dataCriacaoFim = dataCriacaoFim;
    }
}
