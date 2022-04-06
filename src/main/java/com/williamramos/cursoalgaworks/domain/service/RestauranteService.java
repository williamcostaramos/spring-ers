package com.williamramos.cursoalgaworks.domain.service;

import com.williamramos.cursoalgaworks.domain.exception.EntidadeEmUsoException;
import com.williamramos.cursoalgaworks.domain.exception.EntidadeNaoEncontradaException;
import com.williamramos.cursoalgaworks.domain.model.Cozinha;
import com.williamramos.cursoalgaworks.domain.model.Restaurante;
import com.williamramos.cursoalgaworks.domain.repository.CozinhaRepository;
import com.williamramos.cursoalgaworks.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestauranteService {
    @Autowired
    private RestauranteRepository repository;
    @Autowired
    private CozinhaRepository cozinhaRepository;

    public List<Restaurante> listarTodos() {
        List<Restaurante> restaurantes = repository.listAll();
        if (restaurantes == null) {
            throw new EntidadeNaoEncontradaException(String.format("Restaurantes não encontrado"));
        }
        return restaurantes;
    }

    public Restaurante buscar(Long id) {
        try {
            Restaurante restaurante = repository.buscar(id);
            return (restaurante != null ? restaurante: null);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format("Restaurante de codigo %d, não encontrada", id));
        }
    }

    public Restaurante salvar(Restaurante restaurante) {
        Long idCozinha = restaurante.getCozinha().getId();
        Cozinha obj = cozinhaRepository.buscar(idCozinha);
        if (obj == null) {
            throw new EntidadeNaoEncontradaException(String.format("Cozinha de codigo %d, não encontrada", idCozinha));
        }
        return repository.salvar(restaurante);
    }

    public void remover(Long id) {
        try {
            repository.remover(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format("Restaurante de codigo %d, não encontrada", id));
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format("Restaurante de codigo %d não pode ser removida, pois está em uso", id));
        }
    }

}
