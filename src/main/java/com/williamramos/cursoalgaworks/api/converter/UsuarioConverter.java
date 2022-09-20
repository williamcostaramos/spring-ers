package com.williamramos.cursoalgaworks.api.converter;

import com.williamramos.cursoalgaworks.api.model.dto.UsuarioDTO;
import com.williamramos.cursoalgaworks.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component

public class UsuarioConverter implements Converter<Usuario, UsuarioDTO> {
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
    public UsuarioDTO toDTO(Object entity) {
        return modelMapper.map(entity, UsuarioDTO.class);
    }

    @Override
    public List<UsuarioDTO> toDTOList(Collection<Usuario> list) {
        return list.stream().map(Usuario -> toDTO(Usuario)).collect(Collectors.toList());
    }

    @Override
    public void copyToDomainObject(Object input, Object output) {
        modelMapper.map(input, output);
    }

}
