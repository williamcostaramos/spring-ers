package com.williamramos.cursoalgaworks.domain.service;

import com.williamramos.cursoalgaworks.domain.exception.CozinhaEmUsoException;
import com.williamramos.cursoalgaworks.domain.exception.CozinhaNaoEncontradaException;
import com.williamramos.cursoalgaworks.domain.model.Cozinha;
import com.williamramos.cursoalgaworks.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CozinhaService {
    @Autowired
    private CozinhaRepository repository;

    public List<Cozinha> listarTodas() {
        return repository.findAll();
    }

    public Cozinha buscar(Long id) {
        return repository.findById(id).orElseThrow(() -> new CozinhaNaoEncontradaException(id));
    }
    public List<Cozinha> consultarPorNome(String nome){
        return repository.findByNomeContaining(nome);
    }

    @Transactional
    public Cozinha salvar(Cozinha cozinha) {
        return repository.save(cozinha);
    }

    @Transactional
    public void remover(Long id) {
        try {
            repository.deleteById(id);
            repository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new CozinhaNaoEncontradaException( id);
        } catch (DataIntegrityViolationException e) {
            throw new CozinhaEmUsoException( id);
        }
    }
}