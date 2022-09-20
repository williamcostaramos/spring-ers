package com.williamramos.cursoalgaworks.api.converter;

import com.williamramos.cursoalgaworks.api.model.dto.CidadeDTO;
import com.williamramos.cursoalgaworks.domain.model.Cidade;
import com.williamramos.cursoalgaworks.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component

public class CidadeConverter implements Converter<Cidade, CidadeDTO> {
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Cidade dtoToDomain(Object dto) {
        return modelMapper.map(dto, Cidade.class);
    }

    @Override
    public Cidade inputToDomain(Object input) {
        return modelMapper.map(input, Cidade.class);
    }

    @Override
    public CidadeDTO toDTO(Object entity) {
        return modelMapper.map(entity, CidadeDTO.class);
    }

    @Override
    public List<CidadeDTO> toDTOList(Collection<Cidade> list) {
        return list.stream().map(Cidade -> toDTO(Cidade)).collect(Collectors.toList());
    }

    @Override
    public void copyToDomainObject(Object input, Object output) {
        ((Cidade) output).setEstado(new Estado());
        modelMapper.map(input, output);
    }

}
