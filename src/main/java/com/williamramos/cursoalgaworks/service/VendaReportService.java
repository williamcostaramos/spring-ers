package com.williamramos.cursoalgaworks.service;

import com.williamramos.cursoalgaworks.api.model.filter.VendaDiariaFilter;

public interface VendaReportService {
    byte[] emitirRetalorioPdf(VendaDiariaFilter filtro);
}
