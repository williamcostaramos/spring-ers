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

    private final String MSG_COZINHA_NAO_ENCONTRADA = "Cozinha de codigo %d, não encontrada";
    private final String MSG_COZINHA_EM_USO = "Cozinha de codigo %d não pode ser removida, pois está em uso";

    @Autowired
    private CozinhaRepository repository;

    public List<Cozinha> listarTodas() {
        return repository.findAll();
    }

    public Cozinha buscar(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(MSG_COZINHA_NAO_ENCONTRADA));
    }
    public List<Cozinha> consultarPorNome(String nome){
        return repository.findByNomeContaining(nome);
    }

    public Cozinha salvar(Cozinha cozinha) {
        return repository.save(cozinha);
    }

    public void remover(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format(MSG_COZINHA_NAO_ENCONTRADA, id));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_COZINHA_EM_USO, id));
        }
    }
}