package com.williamramos.cursoalgaworks.core.config.properties;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleemailv2.AmazonSimpleEmailServiceV2;
import com.amazonaws.services.simpleemailv2.AmazonSimpleEmailServiceV2ClientBuilder;
import com.williamramos.cursoalgaworks.service.EmailSendService;
import com.williamramos.cursoalgaworks.service.impl.FakeEmailSendServiceImpl;
import com.williamramos.cursoalgaworks.service.impl.SESEmailSendServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfig {
    @Autowired
    private EmailProperties emailProperties;

    @Bean
    public AmazonSimpleEmailServiceV2 SESMail() {
        AWSCredentials credentials = new BasicAWSCredentials(emailProperties.getIdChaveSecreta(), emailProperties.getChaveSecreta());
        AmazonSimpleEmailServiceV2 client;
        client = AmazonSimpleEmailServiceV2ClientBuilder.standard()
                .withRegion(emailProperties.getRegiao())
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
        return client;
    }

    @Bean
    public EmailSendService emailSendService() {
        switch (emailProperties.getImpl()) {
            case "SMTP":
                return new SESEmailSendServiceImpl();
            case "FAKE":
                return new FakeEmailSendServiceImpl();
            default:
                return null;
        }

    }

}
