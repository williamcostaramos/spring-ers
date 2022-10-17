package com.williamramos.cursoalgaworks.service.impl;

import com.amazonaws.services.simpleemailv2.AmazonSimpleEmailServiceV2;
import com.amazonaws.services.simpleemailv2.model.*;
import com.williamramos.cursoalgaworks.core.config.properties.EmailProperties;
import com.williamramos.cursoalgaworks.domain.exception.EmailSendException;
import com.williamramos.cursoalgaworks.domain.exception.NegocioException;
import com.williamramos.cursoalgaworks.service.EmailSendService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.util.stream.Collectors;

@Service
public class FakeEmailSendServiceImpl implements EmailSendService {
    @Autowired
    private EmailProperties properties;
    @Autowired
    private AmazonSimpleEmailServiceV2 mail;

    @Autowired
    private Configuration freeMakerConfiguration;

    @Override
    public void enviar(Mensagem mensagem) {
        String enviar = montarMensagem(mensagem);
        System.out.printf(enviar);

    }
    private String montarMensagem(Mensagem mensagem){
        return String.format("Enviando email\n " +
                " Email:%s \n Assunto: %s \n Mensagem: %s \n", mensagem.getDestinatarios().stream().map(item -> item).collect(Collectors.joining(", ")), mensagem.getAssunto(), mensagem.getMensagem());
    }


}
