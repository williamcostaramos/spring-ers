package com.williamramos.cursoalgaworks.api.model.dto;

import java.math.BigDecimal;
import java.util.Date;

public class VendaDiariaDTO {
    private Date data;
    private Long totalVenda;
    private BigDecimal totalFaturado;

    public VendaDiariaDTO(Date data, Long totalVenda, BigDecimal totalFaturado) {
        this.data = data;
        this.totalVenda = totalVenda;
        this.totalFaturado = totalFaturado;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Long getTotalVenda() {
        return totalVenda;
    }

    public void setTotalVenda(Long totalVenda) {
        this.totalVenda = totalVenda;
    }

    public BigDecimal getTotalFaturado() {
        return totalFaturado;
    }

    public void setTotalFaturado(BigDecimal totalFaturado) {
        this.totalFaturado = totalFaturado;
    }
}
