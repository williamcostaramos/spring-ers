package com.williamramos.cursoalgaworks.arquivo;

import java.io.InputStream;

public class NovaFoto {
    private String nomeArquivo;
    private InputStream inputStream;

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
}
