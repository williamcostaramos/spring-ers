package com.williamramos.cursoalgaworks.api.converter;

import com.williamramos.cursoalgaworks.api.model.dto.EstadoDTO;
import com.williamramos.cursoalgaworks.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component

public class EstadoConverter implements Converter<Estado, EstadoDTO> {
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Estado dtoToDomain(Object dto) {
        return modelMapper.map(dto, Estado.class);
    }

    @Override
    public Estado inputToDomain(Object input) {
        return modelMapper.map(input, Estado.class);
    }


    @Override
    public EstadoDTO toDTO(Object entity) {
        return modelMapper.map(entity, EstadoDTO.class);
    }

    @Override
    public List<EstadoDTO> toDTOList(Collection<Estado> list) {
        return list.stream().map(Estado -> toDTO(Estado)).collect(Collectors.toList());
    }

    @Override
    public void copyToDomainObject(Object input, Object output) {
        modelMapper.map(input, output);
    }

}
