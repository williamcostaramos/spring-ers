package com.williamramos.cursoalgaworks.domain.repository;

import com.williamramos.cursoalgaworks.domain.model.Cidade;

import java.util.List;

public interface CidadeRepository {
    List<Cidade> listAll();
    Cidade findById(Long id);
    Cidade salvar(Cidade cidade);
    void remover(Cidade cidade);
}
