package com.williamramos.cursoalgaworks.domain.service;

import com.williamramos.cursoalgaworks.domain.exception.EntidadeNaoEncontradaException;
import com.williamramos.cursoalgaworks.domain.exception.NegocioException;
import com.williamramos.cursoalgaworks.domain.exception.ProdutoNaoEncontradoException;
import com.williamramos.cursoalgaworks.domain.exception.RestauranteNaoEncontradoException;
import com.williamramos.cursoalgaworks.domain.model.Produto;
import com.williamramos.cursoalgaworks.domain.model.Permissao;
import com.williamramos.cursoalgaworks.domain.model.Restaurante;
import com.williamramos.cursoalgaworks.domain.repository.ProdutoRepository;
import com.williamramos.cursoalgaworks.domain.repository.ProdutoRepository;
import com.williamramos.cursoalgaworks.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository repository;
    @Autowired
    private RestauranteRepository restauranteRepository;

    public List<Produto> listar(Long idRestaurante) {
        List<Produto> produtos = (List<Produto>) repository.findProdutoByRestaurante(idRestaurante);
        if (produtos.isEmpty()) {
            throw new ProdutoNaoEncontradoException("Produto nao encontrado");
        }
        return produtos;
    }

    public Produto buscar(Long idProduto, Long idRestaurante) {
        buscarRestaurante(idRestaurante);
        return repository.findProdutoByIdAndRestaurante(idProduto, idRestaurante).orElseThrow(() -> new ProdutoNaoEncontradoException(idProduto, idRestaurante));
    }



    @Transactional
    public Produto salvar(Produto produto) {
        Long idRestaurante = produto.getRestaurante().getId();
        Restaurante restaurante = buscarRestaurante(idRestaurante);
        produto.setRestaurante(restaurante);
        return repository.save(produto);
    }

    @Transactional
    public void remover(Long idProduto, Long idRestaurante) {
        try {
            Optional<Produto> produto = repository.findProdutoByIdAndRestaurante(idProduto, idRestaurante);
            if (!produto.isPresent()) {
                throw new NegocioException(String.format("Produto %d nÃo encontrado para o restaurante %d", idProduto, idRestaurante));
            }
            repository.deleteById(idProduto);
        } catch (EmptyResultDataAccessException e) {
            throw new ProdutoNaoEncontradoException(idProduto);
        }
    }

    private Restaurante buscarRestaurante(Long idRestaurante) {
        return restauranteRepository.findById(idRestaurante).orElseThrow(() -> new RestauranteNaoEncontradoException(idRestaurante));
    }

}
