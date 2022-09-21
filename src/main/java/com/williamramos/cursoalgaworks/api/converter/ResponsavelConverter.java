package com.williamramos.cursoalgaworks.api.converter;

import com.williamramos.cursoalgaworks.api.model.dto.ResponsavelDTO;
import com.williamramos.cursoalgaworks.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component

public class ResponsavelConverter implements Converter<Usuario, ResponsavelDTO> {
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Usuario dtoToDomain(Object dto) {
        return modelMapper.map(dto, Usuario.class);
    }

    @Override
    public Usuario inputToDomain(Object input) {
        return modelMapper.map(input, Usuario.class);
    }


    @Override
    public ResponsavelDTO toDTO(Object entity) {
        return modelMapper.map(entity, ResponsavelDTO.class);
    }

    @Override
    public List<ResponsavelDTO> toDTOList(Collection<Usuario> list) {
        return list.stream().map(Usuario -> toDTO(Usuario)).collect(Collectors.toList());
    }

    @Override
    public void copyToDomainObject(Object input, Object output) {
        modelMapper.map(input, output);
    }

}
