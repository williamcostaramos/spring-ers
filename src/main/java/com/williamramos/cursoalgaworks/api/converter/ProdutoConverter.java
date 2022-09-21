package com.williamramos.cursoalgaworks.api.converter;

import com.williamramos.cursoalgaworks.api.model.dto.ProdutoDTO;
import com.williamramos.cursoalgaworks.domain.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component

public class ProdutoConverter implements Converter<Produto, ProdutoDTO> {
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Produto dtoToDomain(Object dto) {
        return modelMapper.map(dto, Produto.class);
    }

    @Override
    public Produto inputToDomain(Object input) {
        return modelMapper.map(input, Produto.class);
    }


    @Override
    public ProdutoDTO toDTO(Object entity) {
        return modelMapper.map(entity, ProdutoDTO.class);
    }

    @Override
    public List<ProdutoDTO> toDTOList(Collection<Produto> list) {
        return list.stream().map(Produto -> toDTO(Produto)).collect(Collectors.toList());
    }

    @Override
    public void copyToDomainObject(Object input, Object output) {
        modelMapper.map(input, output);
    }

}
