package com.williamramos.cursoalgaworks.infraestruture.repository.especification;

import com.williamramos.cursoalgaworks.api.model.filter.PedidoFilter;
import com.williamramos.cursoalgaworks.domain.model.Pedido;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class PedidoSpecs {

    public static Specification<Pedido> filtrar(PedidoFilter filtro) {
        return (root, query, builder) -> {
            root.fetch("restaurante").fetch("cozinha");
            root.fetch("cliente");

            List<Predicate> predicates = new ArrayList<Predicate>();

            if (filtro.getClienteId() != null) {
                predicates.add(builder.equal(root.get("cliente"), filtro.getClienteId()));
            }

            if (filtro.getRestauranteId() != null) {
                predicates.add(builder.equal(root.get("restaurante"), filtro.getRestauranteId()));
            }

            if (filtro.getDataCriacaoInicio() != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"),
                        filtro.getDataCriacaoInicio()));
            }

            if (filtro.getDataCriacaoFim() != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"),
                        filtro.getDataCriacaoFim()));
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
