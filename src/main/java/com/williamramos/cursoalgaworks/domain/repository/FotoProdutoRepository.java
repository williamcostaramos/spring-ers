package com.williamramos.cursoalgaworks.domain.repository;

import com.williamramos.cursoalgaworks.domain.model.FotoProduto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FotoProdutoRepository {
    FotoProduto salvar(FotoProduto fotoProduto);

    Optional<FotoProduto> findById(Long idProduto);
}
