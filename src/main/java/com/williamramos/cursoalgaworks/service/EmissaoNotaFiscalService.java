package com.williamramos.cursoalgaworks.service;

import com.williamramos.cursoalgaworks.domain.model.Cliente;
import com.williamramos.cursoalgaworks.observable.NotafiscalEmitidaEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class EmissaoNotaFiscalService {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void emitir(Cliente cliente){
        applicationEventPublisher.publishEvent(new NotafiscalEmitidaEvent(cliente));
    }
}
