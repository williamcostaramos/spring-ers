package com.williamramos.cursoalgaworks.infraestruture.repository;

import com.williamramos.cursoalgaworks.domain.model.Estado;
import com.williamramos.cursoalgaworks.domain.repository.EstadoRepository;
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
    public Estado findById(Long id) {
        return manager.find(Estado.class, id);
    }

    @Override
    public Estado salvar(Estado Estado) {
        return manager.merge(Estado);
    }

    @Override
    public void remover(Estado Estado) {
        Estado obj = findById(Estado.getId());
        if(obj != null){
            manager.remove(obj);
        }
    }
}
