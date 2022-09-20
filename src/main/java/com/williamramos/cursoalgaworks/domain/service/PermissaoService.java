package com.williamramos.cursoalgaworks.domain.service;

import com.williamramos.cursoalgaworks.domain.exception.PermissaoEmUsoException;
import com.williamramos.cursoalgaworks.domain.exception.PermissaoNaoEncontradaException;
import com.williamramos.cursoalgaworks.domain.model.Permissao;
import com.williamramos.cursoalgaworks.domain.repository.PermisaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PermissaoService {
    @Autowired
    private PermisaoRepository repository;
    @Autowired
    private EstadoService estadoService;

    public List<Permissao> listar() {
        List<Permissao> permissoes = repository.findAll();
        if (permissoes.isEmpty()) {
            throw new PermissaoNaoEncontradaException();
        }
        return permissoes;
    }

    public Permissao buscar(Long id) {
        return repository.findById(id).orElseThrow(() -> new PermissaoNaoEncontradaException(id));
    }

    @Transactional
    public Permissao salvar(Permissao Permissao) {
        return repository.save(Permissao);
    }
    @Transactional
    public void remover(Long idPermissao) {
        try {
            this.repository.deleteById(idPermissao);
            this.repository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new PermissaoNaoEncontradaException(idPermissao);
        } catch (DataIntegrityViolationException e) {
            throw new PermissaoEmUsoException(idPermissao);
        }
    }
}
