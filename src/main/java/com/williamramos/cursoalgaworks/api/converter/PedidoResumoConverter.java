package com.williamramos.cursoalgaworks.api.converter;

import com.williamramos.cursoalgaworks.api.model.dto.PedidoResumoDTO;
import com.williamramos.cursoalgaworks.domain.model.Pedido;
import com.williamramos.cursoalgaworks.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component

public class PedidoResumoConverter implements Converter<Pedido, PedidoResumoDTO> {
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Pedido dtoToDomain(Object dto) {
        return modelMapper.map(dto, Pedido.class);
    }

    @Override
    public Pedido inputToDomain(Object input) {
        return modelMapper.map(input, Pedido.class);
    }


    @Override
    public PedidoResumoDTO toDTO(Object entity) {
        return modelMapper.map(entity, PedidoResumoDTO.class);
    }

    @Override
    public List<PedidoResumoDTO> toDTOList(Collection<Pedido> list) {
        return list.stream().map(Pedido -> toDTO(Pedido)).collect(Collectors.toList());
    }

    @Override
    public void copyToDomainObject(Object input, Object output) {
        ((Pedido)output).setCliente(new Usuario());
        modelMapper.map(input, output);
    }

}
