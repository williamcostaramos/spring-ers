package com.williamramos.cursoalgaworks.api.model.filter;

import com.williamramos.cursoalgaworks.domain.model.enums.StatusPedido;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class VendaDiariaFilter {
    private Long retauranteId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dataCriacaoInicio;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dataCriacaoFim;

    private StatusPedido statusPedido;

    public Long getRetauranteId() {
        return retauranteId;
    }

    public void setRetauranteId(Long retauranteId) {
        this.retauranteId = retauranteId;
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

    public StatusPedido getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(StatusPedido statusPedido) {
        this.statusPedido = statusPedido;
    }
}
