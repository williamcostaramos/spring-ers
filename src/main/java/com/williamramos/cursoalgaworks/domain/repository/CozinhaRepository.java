package com.williamramos.cursoalgaworks.domain.repository;

import com.williamramos.cursoalgaworks.domain.model.Cozinha;

import java.util.List;

public interface CozinhaRepository {
    List<Cozinha> listAll();
    Cozinha buscar(Long id);
    Cozinha salvar(Cozinha cozinha);
    void remove(Long id);
}
