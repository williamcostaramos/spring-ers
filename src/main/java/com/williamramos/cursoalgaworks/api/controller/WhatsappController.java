package com.williamramos.cursoalgaworks.api.controller;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/whatsapp")
public class WhatsappController {
    public static final String ACCOUNT_SID = "AC81fa4e6168b8b695bd0ef858fcf9b5a5";
    public static final String AUTH_TOKEN ="babbbda174b1d9b08812b90f54dfcfaf";




    @PostMapping
    public void enviarMensagem(@RequestBody String mensagem){
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);


            Message message = Message.creator(
                    new PhoneNumber("whatsapp:+556381349586"),
                    new PhoneNumber("whatsapp:+14155238886"),
                    mensagem).create();




    }
}
