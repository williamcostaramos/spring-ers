package com.williamramos.cursoalgaworks.jpa;

import com.williamramos.cursoalgaworks.domain.model.Cozinha;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class CadastroCozinha {
    @PersistenceContext
    private EntityManager manager;

    public List<Cozinha> listAll() {
        TypedQuery<Cozinha> query = manager.createQuery("from Cozinha", Cozinha.class);
       return   query.getResultList();

    }

    @Transactional
    public Cozinha salvar(Cozinha cozinha){
        return manager.merge(cozinha);
    }

    public Cozinha findById(Long id){
        return  manager.find(Cozinha.class, id);
    }

    @Transactional
    public void remove(Cozinha obj){
        Cozinha cozinha = findById(obj.getId());
        if (cozinha != null){
            manager.remove(cozinha);
            System.out.printf("Cozinha: %s removida com sucesso", cozinha.getNome());
        }
    }


}
