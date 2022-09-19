package com.williamramos.cursoalgaworks.api.converter;

import com.williamramos.cursoalgaworks.api.model.dto.FormaPagamentoDTO;
import com.williamramos.cursoalgaworks.domain.model.FormaPagamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component

public class FormaPagamentoConverter implements Converter<FormaPagamento, FormaPagamentoDTO> {
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public FormaPagamento dtoToDomain(Object dto) {
        return modelMapper.map(dto, FormaPagamento.class);
    }

    @Override
    public FormaPagamento inputToDomain(Object input) {
        return modelMapper.map(input, FormaPagamento.class);
    }


    @Override
    public FormaPagamentoDTO toDTO(Object entity) {
        return modelMapper.map(entity, FormaPagamentoDTO.class);
    }

    @Override
    public List<FormaPagamentoDTO> toDTOList(Collection<FormaPagamento> list) {
        return list.stream().map(FormaPagamento -> toDTO(FormaPagamento)).collect(Collectors.toList());
    }

    @Override
    public void copyToDomainObject(Object input, Object output) {
        modelMapper.map(input, output);
    }

}
