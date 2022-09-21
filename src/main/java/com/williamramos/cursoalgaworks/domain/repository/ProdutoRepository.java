package com.williamramos.cursoalgaworks.domain.repository;

import com.williamramos.cursoalgaworks.domain.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    @Query("from Produto prod where prod.restaurante.id = :idRestaurante")
    Collection<Produto> findProdutoByRestaurante(Long idRestaurante);
    @Query("from Produto prod where prod.id = :idProduto and prod.restaurante.id = :idRestaurante")
    Optional<Produto> findProdutoByIdAndRestaurante(Long idProduto, Long idRestaurante );
}
