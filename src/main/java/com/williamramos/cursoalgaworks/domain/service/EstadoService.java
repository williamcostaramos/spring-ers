package com.williamramos.cursoalgaworks.domain.service;

import com.williamramos.cursoalgaworks.domain.exception.EntidadeEmUsoException;
import com.williamramos.cursoalgaworks.domain.exception.EntidadeNaoEncontradaException;
import com.williamramos.cursoalgaworks.domain.exception.EstadoNaoEncontradoException;
import com.williamramos.cursoalgaworks.domain.model.Estado;
import com.williamramos.cursoalgaworks.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {

    private final String MSG_ESTADO_EM_USO = "Estado de código %d não pode ser removido, pois está em uso";
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
        return repository.findById(id).orElseThrow(() -> new EstadoNaoEncontradoException(id));
    }

    public Estado salvar(Estado estado) {
        Estado obj = repository.save(estado);
        if (obj != null) {
        }
        return obj;
    }

    public void remover(Long idEstado) {
        try {
            this.repository.deleteById(idEstado);
        } catch (EmptyResultDataAccessException e) {
            throw new EstadoNaoEncontradoException( idEstado);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(String.format(MSG_ESTADO_EM_USO, idEstado));
        }
    }
}
