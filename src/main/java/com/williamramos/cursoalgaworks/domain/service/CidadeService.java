package com.williamramos.cursoalgaworks.domain.service;

import com.williamramos.cursoalgaworks.domain.exception.CidadeEmUsoException;
import com.williamramos.cursoalgaworks.domain.exception.CidadeNaoEncontradaException;
import com.williamramos.cursoalgaworks.domain.exception.EntidadeEmUsoException;
import com.williamramos.cursoalgaworks.domain.exception.EntidadeNaoEncontradaException;
import com.williamramos.cursoalgaworks.domain.model.Cidade;
import com.williamramos.cursoalgaworks.domain.model.Estado;
import com.williamramos.cursoalgaworks.domain.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class CidadeService {
    @Autowired
    private CidadeRepository repository;
    @Autowired
    private EstadoService estadoService;

    public List<Cidade> listarTodos() {
        List<Cidade> Cidades = repository.findAll();
        if (Cidades == null) {
            throw new CidadeNaoEncontradaException();
        }
        return Cidades;
    }

    public Cidade buscar(Long id) {
        return repository.findById(id).orElseThrow(() -> new CidadeNaoEncontradaException(id));
    }

    @Transactional
    public Cidade salvar(Cidade cidade) {
        Estado estado = estadoService.buscar(cidade.getEstado().getId());
        cidade.setEstado(estado);
        return repository.save(cidade);
    }
    @Transactional
    public void remover(Long idCidade) {
        try {
            this.repository.deleteById(idCidade);
        } catch (EmptyResultDataAccessException e) {
            throw new CidadeNaoEncontradaException(idCidade);
        } catch (DataIntegrityViolationException e) {
            throw new CidadeEmUsoException(idCidade);
        }
    }
}
