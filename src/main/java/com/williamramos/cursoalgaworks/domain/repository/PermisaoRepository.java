package com.williamramos.cursoalgaworks.domain.repository;

import com.williamramos.cursoalgaworks.domain.model.Permissao;

import java.util.List;

public interface PermisaoRepository {
    List<Permissao> listAll();
    Permissao buscar(Long id);
    Permissao salvar(Permissao permissao);
    void remover(Permissao permissao);
}
