package com.williamramos.cursoalgaworks.domain.service;

import com.williamramos.cursoalgaworks.domain.model.Estado;
import com.williamramos.cursoalgaworks.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

public class EstadoService {
    @Autowired
    private EstadoRepository repository;

    public List<Estado> listarTodos() {
        List<Estado> estados = repository.listAll();
        if (estados == null) {
            throw new EmptyResultDataAccessException(1);
        }
        return estados;
    }

    public Estado buscar(Long id) {
        Estado obj = repository.buscar(id);
        if (obj == null) {
            throw new EmptyResultDataAccessException(1);
        }
        return obj;
    }

    public Estado salvar(Estado estado){
        Estado obj = repository.salvar(estado);
        if(obj != null){
        }
        return obj;
    }
}
