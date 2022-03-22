package com.williamramos.cursoalgaworks.domain.repository;

import com.williamramos.cursoalgaworks.domain.model.Restaurante;

import java.util.List;

public interface RestauranteRepository {
    List<Restaurante> listAll();
    Restaurante findById(Long id);
    Restaurante salvar(Restaurante obj);
    void remover(Restaurante obj);
}
