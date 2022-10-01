package com.williamramos.cursoalgaworks.core.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.Page;

import java.io.IOException;
@JsonComponent
public class PageJsonSerializer extends JsonSerializer<Page<?>> {
    @Override
    public void serialize(Page<?> page, JsonGenerator json, SerializerProvider serializerProvider) throws IOException {
        json.writeStartObject();
        json.writeObjectField("conteudo", page.getContent());
        json.writeNumberField("tamanho", page.getSize());
        json.writeNumberField("totalElementos", page.getTotalElements());
        json.writeNumberField("totalPaginas", page.getTotalPages());
        json.writeNumberField("paginaAtual", page.getNumber());
        json.writeEndObject();

    }
}
