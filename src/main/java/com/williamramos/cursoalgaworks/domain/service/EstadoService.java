package com.williamramos.cursoalgaworks.domain.service;

import com.williamramos.cursoalgaworks.domain.exception.EntidadeEmUsoException;
import com.williamramos.cursoalgaworks.domain.exception.EntidadeNaoEncontradaException;
import com.williamramos.cursoalgaworks.domain.exception.EstadoEmUsoException;
import com.williamramos.cursoalgaworks.domain.exception.EstadoNaoEncontradoException;
import com.williamramos.cursoalgaworks.domain.model.Estado;
import com.williamramos.cursoalgaworks.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EstadoService {
    @Autowired
    private EstadoRepository repository;

    public List<Estado> listarTodos() {
        List<Estado> estados = repository.findAll();
        if (estados == null) {
            throw new EstadoNaoEncontradoException();
        }
        return estados;
    }

    public Estado buscar(Long id) {
        return repository.findById(id).orElseThrow(() -> new EstadoNaoEncontradoException(id));
    }
    @Transactional
    public Estado salvar(Estado estado) {
        Estado obj = repository.save(estado);
        if (obj != null) {
        }
        return obj;
    }

    @Transactional
    public void remover(Long idEstado) {
        try {
            this.repository.deleteById(idEstado);
        } catch (EmptyResultDataAccessException e) {
            throw new EstadoNaoEncontradoException(idEstado);
        } catch (DataIntegrityViolationException e) {
            throw new EstadoEmUsoException(idEstado);
        }
    }
}
