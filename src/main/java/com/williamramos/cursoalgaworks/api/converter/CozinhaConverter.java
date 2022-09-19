package com.williamramos.cursoalgaworks.api.converter;

import com.williamramos.cursoalgaworks.api.model.dto.CozinhaDTO;
import com.williamramos.cursoalgaworks.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component

public class CozinhaConverter implements Converter<Cozinha, CozinhaDTO> {
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Cozinha dtoToDomain(Object dto) {
        return modelMapper.map(dto, Cozinha.class);
    }

    @Override
    public Cozinha inputToDomain(Object input) {
        return modelMapper.map(input, Cozinha.class);
    }


    @Override
    public CozinhaDTO toDTO(Object entity) {
        return modelMapper.map(entity, CozinhaDTO.class);
    }

    @Override
    public List<CozinhaDTO> toDTOList(Collection<Cozinha> list) {
        return list.stream().map(cozinha -> toDTO(cozinha)).collect(Collectors.toList());
    }

    @Override
    public void copyToDomainObject(Object input, Object output) {
        modelMapper.map(input, output);
    }

}
