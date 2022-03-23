package com.williamramos.cursoalgaworks.domain.service;

import com.williamramos.cursoalgaworks.domain.exception.EntidadeEmUsoException;
import com.williamramos.cursoalgaworks.domain.exception.EntidadeNaoEncontradaException;
import com.williamramos.cursoalgaworks.domain.model.Cozinha;
import com.williamramos.cursoalgaworks.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CozinhaService {
    @Autowired
    private CozinhaRepository repository;

    public List<Cozinha> listarTodas() {
        return repository.listAll();
    }

    public Cozinha buscar(Long id) {
        return repository.finById(id);
    }

    public Cozinha salvar(Cozinha cozinha) {
        return repository.salvar(cozinha);
    }

    public void remover(Long id) {
        try {
            repository.remove(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format("Cozinha de codigo %d, não encontrada", id));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format("Cozinha de codigo %d não pode ser removida, pois está em uso", id));
        }
    }
}