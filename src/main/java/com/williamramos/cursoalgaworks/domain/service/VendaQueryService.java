package com.williamramos.cursoalgaworks.domain.service;

import com.williamramos.cursoalgaworks.api.model.dto.VendaDiariaDTO;
import com.williamramos.cursoalgaworks.api.model.filter.VendaDiariaFilter;

import java.util.List;

public interface VendaQueryService {
    List<VendaDiariaDTO> consultarVendasDiarias(VendaDiariaFilter filtro);
}
