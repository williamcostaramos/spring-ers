package com.williamramos.cursoalgaworks.domain.service.impl;

import com.williamramos.cursoalgaworks.api.model.dto.VendaDiariaDTO;
import com.williamramos.cursoalgaworks.api.model.filter.VendaDiariaFilter;
import com.williamramos.cursoalgaworks.domain.model.Pedido;
import com.williamramos.cursoalgaworks.domain.model.enums.StatusPedido;
import com.williamramos.cursoalgaworks.domain.service.VendaQueryService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class VendaQuerySericeImpl implements VendaQueryService {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<VendaDiariaDTO> consultarVendasDiarias(VendaDiariaFilter filtro) {
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<VendaDiariaDTO> criteriaQuery = criteriaBuilder.createQuery(VendaDiariaDTO.class);
        Root<Pedido> root = criteriaQuery.from(Pedido.class);
        Expression data = criteriaBuilder.function("date", Date.class, root.get("dataCriacao"));
        Selection<VendaDiariaDTO> selection = criteriaBuilder.construct(VendaDiariaDTO.class, data, criteriaBuilder.count(root.get("id")), criteriaBuilder.sum(root.get("valorTotal")));
        criteriaQuery.select(selection);
        criteriaQuery.groupBy(data);
        criteriaQuery.where(filtrarDados(filtro, criteriaBuilder, root));

        return manager.createQuery(criteriaQuery).getResultList();
    }

    private Predicate[] filtrarDados(VendaDiariaFilter filter, CriteriaBuilder builder, Root<Pedido> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (filter.getRetauranteId() != null) {
            predicates.add(builder.equal(root.get("restaurante"), filter.getRetauranteId()));
        }
        if (filter.getDataCriacaoInicio() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"), filter.getDataCriacaoInicio()));
        }
        if (filter.getDataCriacaoFim() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"), filter.getDataCriacaoFim()));
        }

        predicates.add(root.get("statusPedido").in(StatusPedido.CONFIRMADO, StatusPedido.ENTREGUE));
        return predicates.toArray(new Predicate[0]);
    }
}
