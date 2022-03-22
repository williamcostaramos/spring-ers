package com.williamramos.cursoalgaworks.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.williamramos.cursoalgaworks.domain.model.Cozinha;

import java.util.List;
@JacksonXmlRootElement(localName = "cozinhas")
public class CozinhaWrapper {


    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonProperty("cozinha")
    private List<Cozinha> cozinhas;

    public CozinhaWrapper(List<Cozinha> cozinhas) {
        this.cozinhas = cozinhas;
    }

    public List<Cozinha> getCozinhas() {
        return cozinhas;
    }

    public void setCozinhas(List<Cozinha> cozinhas) {
        this.cozinhas = cozinhas;
    }
}
