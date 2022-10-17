package com.williamramos.cursoalgaworks.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public interface EmailSendService {
    void enviar(Mensagem mensagem);

    public class Mensagem {
        private String Assunto;
        private String mensagem;
        private Set<String> destinatarios = new HashSet<>();
        private Map<String, Object> variaveis = new HashMap<>();

        public String getAssunto() {
            return Assunto;
        }

        public void setAssunto(String assunto) {
            Assunto = assunto;
        }

        public String getMensagem() {
            return mensagem;
        }

        public void setMensagem(String mensagem) {
            this.mensagem = mensagem;
        }

        public Set<String> getDestinatarios() {
            return destinatarios;
        }

        public void setDestinatarios(Set<String> destinatarios) {
            this.destinatarios = destinatarios;
        }

        public void destinatario(String destinatario) {
            this.destinatarios.add(destinatario);
        }

        public Map<String, Object> getVariaveis() {
            return variaveis;
        }

        public void setVariaveis(Map<String, Object> variaveis) {
            this.variaveis = variaveis;
        }

        public void variavel(String nomeCampo, Object dados) {
            this.variaveis.put(nomeCampo, dados);
        }
    }
}
