package com.williamramos.cursoalgaworks.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.williamramos.cursoalgaworks.domain.model.Restaurante;

import java.util.List;

@JacksonXmlRootElement(localName = "restaurantes")
public class RestauranteWrapper {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonProperty("restaurante")
    private List<Restaurante> restaurantes;

    public RestauranteWrapper(List<Restaurante> restaurantes) {
        this.restaurantes = restaurantes;
    }
}
