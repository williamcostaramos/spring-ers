package com.williamramos.cursoalgaworks.api.converter;

import com.williamramos.cursoalgaworks.api.model.dto.ItemPedidoDTO;
import com.williamramos.cursoalgaworks.domain.model.ItemPedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component

public class ItemPedidoConverter implements Converter<ItemPedido, ItemPedidoDTO> {
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ItemPedido dtoToDomain(Object dto) {
        return modelMapper.map(dto, ItemPedido.class);
    }

    @Override
    public ItemPedido inputToDomain(Object input) {
        return modelMapper.map(input, ItemPedido.class);
    }


    @Override
    public ItemPedidoDTO toDTO(Object entity) {
        return modelMapper.map(entity, ItemPedidoDTO.class);
    }

    @Override
    public List<ItemPedidoDTO> toDTOList(Collection<ItemPedido> list) {
        return list.stream().map(ItemPedido -> toDTO(ItemPedido)).collect(Collectors.toList());
    }

    @Override
    public void copyToDomainObject(Object input, Object output) {
        modelMapper.map(input, output);
    }

}
