package com.williamramos.cursoalgaworks.api.controller;

import com.williamramos.cursoalgaworks.api.model.dto.VendaDiariaDTO;
import com.williamramos.cursoalgaworks.api.model.filter.VendaDiariaFilter;
import com.williamramos.cursoalgaworks.domain.service.VendaQueryService;
import com.williamramos.cursoalgaworks.service.VendaReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estatisticas")
public class EstatisticaController {
    @Autowired
    private VendaQueryService vendaQueryService;

    @Autowired
    private VendaReportService vendaReportService;

    @GetMapping(path = "/vendas-diaria", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VendaDiariaDTO> consultaVendasDiaria(VendaDiariaFilter filter) {
        return vendaQueryService.consultarVendasDiarias(filter);
    }

    @GetMapping(path = "/vendas-diaria", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> consultaVendasDiariaPDF(VendaDiariaFilter filter) {
        byte [] pdf = vendaReportService.emitirRetalorioPdf(filter);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=vendas-diarias.pdf");
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).headers(headers).body(pdf);
    }

}
