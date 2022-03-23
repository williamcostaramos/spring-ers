package com.williamramos.cursoalgaworks.infraestruture.repository;

import com.williamramos.cursoalgaworks.domain.model.Cidade;
import com.williamramos.cursoalgaworks.domain.repository.CidadeRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class CidadeRepositoryImpl implements CidadeRepository {
    @PersistenceContext
    private EntityManager manager;
    @Override
    public List<Cidade> listAll() {
        return manager.createQuery("from Cidade", Cidade.class).getResultList();
    }

    @Override
    public Cidade findById(Long id) {
        return manager.find(Cidade.class, id);
    }

    @Override
    public Cidade salvar(Cidade cidade) {

        return manager.merge(cidade);
    }

    @Override
    public void remover(Cidade cidade) {
        Cidade obj = findById(cidade.getId());
        if(obj != null){
            manager.remove(obj);
        }

    }
}
