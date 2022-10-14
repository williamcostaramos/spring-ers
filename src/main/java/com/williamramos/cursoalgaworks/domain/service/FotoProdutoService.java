package com.williamramos.cursoalgaworks.domain.service;

import com.williamramos.cursoalgaworks.domain.exception.FotoNaoEncontradaException;
import com.williamramos.cursoalgaworks.domain.exception.NegocioException;
import com.williamramos.cursoalgaworks.domain.model.FotoProduto;
import com.williamramos.cursoalgaworks.domain.repository.FotoProdutoRepository;
import com.williamramos.cursoalgaworks.service.StoragefileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Repository
public class FotoProdutoService implements FotoProdutoRepository {
    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private StoragefileService storagefileService;

    @Override
    @Transactional
    public FotoProduto salvar(FotoProduto fotoProduto) {
        FotoProduto foto = findById(fotoProduto.getProduto().getId());
        if (foto != null) {
            storagefileService.remover(foto.getNomeArquivo());
            manager.remove(foto);
        }

        return manager.merge(fotoProduto);
    }

    @Override
    public FotoProduto findById(Long idProduto) {
        return manager.find(FotoProduto.class, idProduto);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        try {
            FotoProduto fotoProduto = findById(id);
            if (fotoProduto != null) {
                manager.remove(fotoProduto);
            }
        } catch (NoResultException e) {
            throw new FotoNaoEncontradaException();
        }

    }
}

