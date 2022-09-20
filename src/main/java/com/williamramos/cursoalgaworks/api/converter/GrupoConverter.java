package com.williamramos.cursoalgaworks.api.converter;

import com.williamramos.cursoalgaworks.api.model.dto.GrupoDTO;
import com.williamramos.cursoalgaworks.domain.model.Grupo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component

public class GrupoConverter implements Converter<Grupo, GrupoDTO> {
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Grupo dtoToDomain(Object dto) {
        return modelMapper.map(dto, Grupo.class);
    }

    @Override
    public Grupo inputToDomain(Object input) {
        return modelMapper.map(input, Grupo.class);
    }


    @Override
    public GrupoDTO toDTO(Object entity) {
        return modelMapper.map(entity, GrupoDTO.class);
    }

    @Override
    public List<GrupoDTO> toDTOList(Collection<Grupo> list) {
        return list.stream().map(Grupo -> toDTO(Grupo)).collect(Collectors.toList());
    }

    @Override
    public void copyToDomainObject(Object input, Object output) {
        modelMapper.map(input, output);
    }

}
