package com.williamramos.cursoalgaworks.domain.service;

import com.williamramos.cursoalgaworks.domain.exception.EntidadeNaoEncontradaException;
import com.williamramos.cursoalgaworks.domain.exception.GrupoNaoEncontradoException;
import com.williamramos.cursoalgaworks.domain.model.Grupo;
import com.williamramos.cursoalgaworks.domain.model.Permissao;
import com.williamramos.cursoalgaworks.domain.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GrupoService {
    @Autowired
    private GrupoRepository repository;
    @Autowired
    private PermissaoService permissaoService;

    public List<Grupo> listar() {
        List<Grupo> Grupos = repository.findAll();
        if (Grupos.isEmpty()) {
            throw new GrupoNaoEncontradoException("Grupo nao encontrado");
        }
        return repository.findAll();
    }

    public Grupo buscar(Long id) {
        return repository.findById(id).orElseThrow(() -> new GrupoNaoEncontradoException(id));
    }

    @Transactional
    public Grupo salvar(Grupo Grupo) {
        return repository.save(Grupo);
    }

    @Transactional
    public void remover(Long id) {
        try {
            Grupo Grupo = repository.getById(id);
            if (Grupo != null) {
                repository.deleteById(id);
            }
        } catch (EntidadeNaoEncontradaException e) {
            throw new GrupoNaoEncontradoException(id);
        }
    }
    @Transactional
    public void associarPermissao(Long idGrupo, long idPremissao) {
        Grupo grupo = buscar(idGrupo);
        Permissao permissao = this.permissaoService.buscar(idPremissao);
        grupo.adicionarPermissao(permissao);

    }
    @Transactional
    public void desassociarPermissao(Long idGrupo, long idPremissao) {
        Grupo grupo = buscar(idGrupo);
        Permissao permissao = this.permissaoService.buscar(idPremissao);
        grupo.removerPermissao(permissao);

    }

}
