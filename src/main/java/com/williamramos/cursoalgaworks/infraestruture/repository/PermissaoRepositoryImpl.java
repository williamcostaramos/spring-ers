package com.williamramos.cursoalgaworks.infraestruture.repository;

import com.williamramos.cursoalgaworks.domain.model.Permissao;
import com.williamramos.cursoalgaworks.domain.repository.PermisaoRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Component
public class PermissaoRepositoryImpl implements PermisaoRepository {
    @PersistenceContext
    private EntityManager manager;
    @Override
    public List<Permissao> listAll() {
        return manager.createQuery("from Permissao", Permissao.class).getResultList();
    }

    @Override
    public Permissao findById(Long id) {
        return manager.find(Permissao.class, id);
    }

    @Override
    public Permissao salvar(Permissao permissao) {
        return manager.merge(permissao);
    }

    @Override
    public void remover(Permissao permissao) {
        Permissao obj = findById(permissao.getId());
        if(obj != null){
            manager.remove(obj);
        }
    }
}
