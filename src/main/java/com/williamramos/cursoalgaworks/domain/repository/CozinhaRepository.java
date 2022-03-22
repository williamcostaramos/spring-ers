package com.williamramos.cursoalgaworks.domain.repository;

import com.williamramos.cursoalgaworks.domain.model.Cozinha;
import org.springframework.stereotype.Component;

import java.util.List;

public interface CozinhaRepository {
    List<Cozinha> listAll();
    Cozinha finById(Long id);
    Cozinha salvar(Cozinha cozinha);
    void remove(Cozinha cozinha);
}
