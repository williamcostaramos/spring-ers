package com.williamramos.cursoalgaworks.arquivo;

import java.io.InputStream;

public class FotoRecuperada {
    private InputStream inputStream;
    private String url;

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public boolean hasUrl(){
        return url !=null;
    }
    public boolean hasInputStream(){
        return inputStream !=null;
    }
}
