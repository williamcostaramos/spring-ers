package com.williamramos.cursoalgaworks.infraestruture.repository;

import com.williamramos.cursoalgaworks.domain.model.Cozinha;
import com.williamramos.cursoalgaworks.domain.model.Restaurante;
import com.williamramos.cursoalgaworks.domain.repository.RestauranteRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Component
public class RestauranteRepositoryImpl implements RestauranteRepository {
    @PersistenceContext
    private EntityManager manager;
    @Override
    public List<Restaurante> listAll() {
        return manager.createQuery("from Restaurante", Restaurante.class).getResultList() ;
    }

    @Override
    public Restaurante findById(Long id) {
        return manager.find(Restaurante.class, id);
    }

    @Transactional
    @Override
    public Restaurante salvar(Restaurante obj) {
        return manager.merge(obj);
    }

    @Transactional
    @Override
    public void remover(Restaurante obj) {
        Restaurante restaurante = findById(obj.getId());
        manager.remove(restaurante);
    }
}
