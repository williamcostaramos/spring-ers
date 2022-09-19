package com.williamramos.cursoalgaworks.domain.service;

import com.williamramos.cursoalgaworks.domain.exception.EntidadeNaoEncontradaException;
import com.williamramos.cursoalgaworks.domain.exception.FormaPagamentoNaoEncontradaException;
import com.williamramos.cursoalgaworks.domain.model.FormaPagamento;
import com.williamramos.cursoalgaworks.domain.model.Restaurante;
import com.williamramos.cursoalgaworks.domain.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FormaPagamentoService {
    @Autowired
    private FormaPagamentoRepository repository;

    public List<FormaPagamento> listar() {
        List<FormaPagamento> formaPagamentos = repository.findAll();
        if (formaPagamentos.isEmpty()) {
            throw new FormaPagamentoNaoEncontradaException("Formas de Pagamento nao encontrada");
        }
        return repository.findAll();
    }

    public FormaPagamento buscar(Long id) {
        return repository.findById(id).orElseThrow(() -> new FormaPagamentoNaoEncontradaException(id));
    }

    @Transactional
    public FormaPagamento salvar(FormaPagamento formaPagamento) {
        return repository.save(formaPagamento);
    }

    @Transactional
    public void remover(Long id) {
        try {
            FormaPagamento formaPagamento = repository.getById(id);
            if (formaPagamento != null) {
                repository.deleteById(id);
            }
        } catch (EntidadeNaoEncontradaException e) {
            throw new FormaPagamentoNaoEncontradaException(id);
        }


    }
}
