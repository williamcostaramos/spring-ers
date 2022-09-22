package com.williamramos.cursoalgaworks.core.config;

import com.williamramos.cursoalgaworks.api.model.input.ItemPedidoInput;
import com.williamramos.cursoalgaworks.domain.model.ItemPedido;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.createTypeMap(ItemPedidoInput.class, ItemPedido.class).addMappings(map -> map.skip(ItemPedido::setId));
        return mapper;
    }
}
