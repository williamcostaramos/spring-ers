package com.williamramos.cursoalgaworks.core.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("algafood.email")
public class EmailProperties {
    private String idChaveSecreta;
    private String chaveSecreta;
    private String regiao;
    private String remetente;

    private String impl;

    private String charset;

    public String getIdChaveSecreta() {
        return idChaveSecreta;
    }

    public void setIdChaveSecreta(String idChaveSecreta) {
        this.idChaveSecreta = idChaveSecreta;
    }

    public String getChaveSecreta() {
        return chaveSecreta;
    }

    public void setChaveSecreta(String chaveSecreta) {
        this.chaveSecreta = chaveSecreta;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public String getImpl() {
        return impl;
    }

    public void setImpl(String impl) {
        this.impl = impl;
    }

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }
}
