package com.williamramos.cursoalgaworks.api.converter;

import com.williamramos.cursoalgaworks.api.model.RestauranteDTO;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface Converter<K, T>{
    K dtoToDomain(Object dto);
    K inputToDomain(Object input);
    T toDTO(Object entity);
    List<T> toDTOCollect(List<K> list);
}
