package com.williamramos.cursoalgaworks.domain.repository;

import com.williamramos.cursoalgaworks.domain.model.Estado;

import java.util.List;

public interface EstadoRepository{
    List<Estado> listAll();
    Estado buscar(Long id);
    Estado salvar(Estado estado);
    void remover(Long id);

}
