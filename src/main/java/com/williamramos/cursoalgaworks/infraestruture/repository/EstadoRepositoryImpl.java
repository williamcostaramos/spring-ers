package com.williamramos.cursoalgaworks.infraestruture.repository;

import com.williamramos.cursoalgaworks.domain.model.Estado;
import com.williamramos.cursoalgaworks.domain.repository.EstadoRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class EstadoRepositoryImpl implements EstadoRepository {
    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Estado> listAll() {
        return manager.createQuery("from Estado", Estado.class).getResultList();
    }

    @Override
    public Estado buscar(Long id) {
        return manager.find(Estado.class, id);
    }

    @Override
    public Estado salvar(Estado Estado) {
        return manager.merge(Estado);
    }

    @Override
    public void remover(Long id) {
        Estado obj = buscar(id);
        if (obj == null) {
            throw new EmptyResultDataAccessException(1);
        }
        manager.remove(obj);

    }
}
