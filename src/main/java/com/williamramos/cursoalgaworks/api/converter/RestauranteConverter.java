package com.williamramos.cursoalgaworks.api.converter;

import com.williamramos.cursoalgaworks.api.model.RestauranteDTO;
import com.williamramos.cursoalgaworks.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component

public class RestauranteConverter implements Converter<Restaurante, RestauranteDTO> {
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Restaurante dtoToDomain(Object dto) {
        return modelMapper.map(dto, Restaurante.class);
    }

    @Override
    public Restaurante inputToDomain(Object input) {
        return modelMapper.map(input, Restaurante.class);
    }


    @Override
    public RestauranteDTO toDTO(Object entity) {
        return modelMapper.map(entity, RestauranteDTO.class);
    }

    @Override
    public List<RestauranteDTO> toDTOCollect(List<Restaurante> list) {
        return list.stream().map(restaurante -> toDTO(restaurante)).collect(Collectors.toList());
    }


}
