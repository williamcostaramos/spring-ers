package com.williamramos.cursoalgaworks.service.impl;

import com.williamramos.cursoalgaworks.api.model.dto.VendaDiariaDTO;
import com.williamramos.cursoalgaworks.api.model.filter.VendaDiariaFilter;
import com.williamramos.cursoalgaworks.domain.service.VendaQueryService;
import com.williamramos.cursoalgaworks.service.VendaReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.*;

@Service
public class VendaReportSericeImpl implements VendaReportService {
    @Autowired
    private VendaQueryService vendaQueryService;

    @Override
    public byte[] emitirRetalorioPdf(VendaDiariaFilter filtro) {
        InputStream inputStream = this.getClass().getResourceAsStream("/relatorios/vendas-diarias.jasper");
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
        List<VendaDiariaDTO> vendaDiaria = vendaQueryService.consultarVendasDiarias(filtro);

        return null;
    }
}
