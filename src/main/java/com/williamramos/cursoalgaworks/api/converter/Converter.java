package com.williamramos.cursoalgaworks.api.converter;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
@Component
public interface Converter<K, T>{
    /**
     * Convert DTO em entidade
     * @param dto
     * @return Entity
     */
    K dtoToDomain(Object dto);

    /**
     * Convert Input em entidade
     * @param input
     * @return K
     */
    K inputToDomain(Object input);

    /**
     * Converte entidade em DTO
     * @param entity
     * @return T
     */
    T toDTO(Object entity);

    /**
     * Converte uma lista de entidades em DTO
     * @param list
     * @return lista de DTO
     */
    List<T> toDTOList(Collection<K> list);


    /**
     * Cria uma copia de um objeto para outro copiando as propriedades definidas
     * @param input
     * @param output
     */
    void copyToDomainObject(Object input, Object output);
}
