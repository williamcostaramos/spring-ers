package com.williamramos.cursoalgaworks.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.williamramos.cursoalgaworks.domain.model.Restaurante;

import java.util.ArrayList;
import java.util.List;

public class CozinhaMixin {
    @JsonIgnore
    private List<Restaurante> restaurantes = new ArrayList<>();
}
