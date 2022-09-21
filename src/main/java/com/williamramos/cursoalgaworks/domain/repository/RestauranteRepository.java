package com.williamramos.cursoalgaworks.domain.repository;

import com.williamramos.cursoalgaworks.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long>, RestauranteRepositoryQueries, JpaSpecificationExecutor<Restaurante> {
    @Query("select distinct rest from Restaurante rest join fetch rest.cozinha left join fetch rest.formaPagamentos left join fetch rest.produtos")
    List<Restaurante> findAll();
    List<Restaurante> findByNomeContaining(String nome);
    List<Restaurante> findByNomeAndCozinhaId(String nome, Long cozinhaId);

    /**
     * Buscar restaurantes filtrando pelo
     * nome e um intervalo de taxafrete
     * @param nome
     * @param taxaFreteInicial
     * @param taxaFreteFinal
     * @return Lista de Restaurantes
     */

    List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);


}
