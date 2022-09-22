package com.williamramos.cursoalgaworks.domain.repository;

import com.williamramos.cursoalgaworks.domain.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    Optional<Pedido> findByCodigo(String codigo);
    @Query("from Pedido p join fetch p.cliente join fetch p.restaurante")
    List<Pedido> findAll();
    @Query("from Pedido pedido where pedido.restaurante.id =:idRestaurante")
    List<Pedido> buscarPorRestaurante(Long idRestaurante);
}
