package com.williamramos.cursoalgaworks.api.converter;

import com.williamramos.cursoalgaworks.api.model.dto.PermissaoDTO;
import com.williamramos.cursoalgaworks.domain.model.Permissao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component

public class PermissaoConverter implements Converter<Permissao, PermissaoDTO> {
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Permissao dtoToDomain(Object dto) {
        return modelMapper.map(dto, Permissao.class);
    }

    @Override
    public Permissao inputToDomain(Object input) {
        return modelMapper.map(input, Permissao.class);
    }


    @Override
    public PermissaoDTO toDTO(Object entity) {
        return modelMapper.map(entity, PermissaoDTO.class);
    }

    @Override
    public List<PermissaoDTO> toDTOList(Collection<Permissao> list) {
        return list.stream().map(Permissao -> toDTO(Permissao)).collect(Collectors.toList());
    }

    @Override
    public void copyToDomainObject(Object input, Object output) {
        modelMapper.map(input, output);
    }

}
