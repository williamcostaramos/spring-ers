package com.williamramos.cursoalgaworks.api.converter;

import com.williamramos.cursoalgaworks.api.model.dto.FotoProdutoDTO;
import com.williamramos.cursoalgaworks.domain.model.FotoProduto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component

public class FotoProdutoConverter implements Converter<FotoProduto, FotoProdutoDTO> {
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public FotoProduto dtoToDomain(Object dto) {
        return modelMapper.map(dto, FotoProduto.class);
    }

    @Override
    public FotoProduto inputToDomain(Object input) {
        return modelMapper.map(input, FotoProduto.class);
    }


    @Override
    public FotoProdutoDTO toDTO(Object entity) {
        return modelMapper.map(entity, FotoProdutoDTO.class);
    }

    @Override
    public List<FotoProdutoDTO> toDTOList(Collection<FotoProduto> list) {
        return list.stream().map(FotoProduto -> toDTO(FotoProduto)).collect(Collectors.toList());
    }

    @Override
    public void copyToDomainObject(Object input, Object output) {
        modelMapper.map(input, output);
    }

}
