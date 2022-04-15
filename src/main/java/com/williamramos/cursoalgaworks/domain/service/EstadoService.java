package com.williamramos.cursoalgaworks.domain.service;

import com.williamramos.cursoalgaworks.domain.model.Estado;
import com.williamramos.cursoalgaworks.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

public class EstadoService {
    @Autowired
    private EstadoRepository repository;

    public List<Estado> listarTodos() {
        List<Estado> estados = repository.findAll();
        if (estados == null) {
            throw new EmptyResultDataAccessException(1);
        }
        return estados;
    }

    public Estado buscar(Long id) {
        Estado obj = repository.findById(id).get();
        if (obj == null) {
            throw new EmptyResultDataAccessException(1);
        }
        return obj;
    }

    public Estado salvar(Estado estado){
        Estado obj = repository.save(estado);
        if(obj != null){
        }
        return obj;
    }
}
