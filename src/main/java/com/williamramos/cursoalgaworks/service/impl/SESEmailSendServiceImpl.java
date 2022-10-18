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

import java.io.IOException;

@Service
public class SESEmailSendServiceImpl implements EmailSendService {
    @Autowired
    private EmailProperties properties;
    @Autowired
    private AmazonSimpleEmailServiceV2 mail;

    @Autowired
    private Configuration freeMakerConfiguration;

    @Override
    public void enviar(Mensagem mensagem) {
        try {
            SendEmailRequest request = montarRequest(mensagem);
            mail.sendEmail(request);

        } catch (Exception e) {
            throw new NegocioException("Nao foi possivel enviar email de confirmacao", e);
        }

    }

    private SendEmailRequest montarRequest(Mensagem mensagem) {
        Destination destinatarios = new Destination().withToAddresses(mensagem.getDestinatarios().toArray(new String[0]));

        Content content = new Content();
        content.setCharset(properties.getCharset());
        content.setData(transformaDadosParaTemplate(mensagem));

        Content suject = new Content();
        suject.setCharset(properties.getCharset());
        suject.setData(mensagem.getAssunto());

        Body body = new Body();
        body.setHtml(content);

        Message message = new Message();
        message.setBody(body);
        message.setSubject(suject);

        EmailContent emailContent = new EmailContent();
        emailContent.setSimple(message);
        SendEmailRequest request = new SendEmailRequest();
        request.setContent(emailContent);
        request.setDestination(destinatarios);
        request.setFromEmailAddress(properties.getRemetente());
        return request;
    }

    private String transformaDadosParaTemplate(Mensagem mensagem) {
        try {
            Template template = freeMakerConfiguration.getTemplate(mensagem.getMensagem());
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, mensagem.getVariaveis());
        } catch (Exception e) {
            throw new EmailSendException("Erro ao enviar email", e);
        }

    }
}
