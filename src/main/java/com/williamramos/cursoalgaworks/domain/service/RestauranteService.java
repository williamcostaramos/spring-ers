package com.williamramos.cursoalgaworks.domain.service;

import com.williamramos.cursoalgaworks.domain.exception.*;
import com.williamramos.cursoalgaworks.domain.model.Cidade;
import com.williamramos.cursoalgaworks.domain.model.Cozinha;
import com.williamramos.cursoalgaworks.domain.model.FormaPagamento;
import com.williamramos.cursoalgaworks.domain.model.Restaurante;
import com.williamramos.cursoalgaworks.domain.repository.CidadeRepository;
import com.williamramos.cursoalgaworks.domain.repository.CozinhaRepository;
import com.williamramos.cursoalgaworks.domain.repository.RestauranteRepository;
import com.williamramos.cursoalgaworks.infraestruture.repository.especification.RestauranteSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class RestauranteService {



    @Autowired
    private RestauranteRepository repository;
    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    public List<Restaurante> listarTodos() {
        List<Restaurante> restaurantes = repository.findAll();
        if (restaurantes == null) {
            throw new RestauranteNaoEncontradoException(String.format("Restaurantes não encontrado"));
        }
        return restaurantes;
    }

    public Restaurante buscar(Long id) {
        return repository.findById(id).orElseThrow(() -> new RestauranteNaoEncontradoException(id));
    }

    public List<Restaurante> buscarPorNomeTaxaFrete(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
        return repository.find(nome, taxaFreteInicial, taxaFreteFinal);
    }

    public List<Restaurante> restauranteComFreteGratis(String nome) {

        return repository.findAll(RestauranteSpecs.comFreteGratis().and(RestauranteSpecs.comNomeSemelhante(nome)));
    }

    @Transactional
    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = cozinhaRepository.findById(cozinhaId).orElseThrow(() -> new  CozinhaNaoEncontradaException(cozinhaId));
        Long cidadeId = restaurante.getEndereco().getCidade().getId();
        Cidade cidade = cidadeRepository.findById(cidadeId).orElseThrow(() -> new CidadeNaoEncontradaException(cidadeId));
        restaurante.setCozinha(cozinha);
        restaurante.getEndereco().setCidade(cidade);
        return repository.save(restaurante);
    }

    @Transactional
    public void remover(Long id) {
        try {
            repository.deleteById(id);
            repository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new RestauranteNaoEncontradoException(id);
        } catch (DataIntegrityViolationException e) {
            throw new RestauranteEmUsoException(id);
        }
    }

    @Transactional
    public void ativar(Long id){
        Restaurante restauranteAtual = buscar(id);
        restauranteAtual.ativar();
    }

    @Transactional
    public void inativar(Long id){
        Restaurante restauranteAtual = buscar(id);
        restauranteAtual.inativar();
    }
    @Transactional
    public void associarFormaPagamento(Long idRestaurante, Long idPagamento){
        Restaurante restaurante = buscar(idRestaurante);
        FormaPagamento formaPagamento= this.formaPagamentoService.buscar(idPagamento);
        restaurante.adicionarFormaPagamento(formaPagamento);
    }
    @Transactional
    public void desassociarFormaPagamento(Long idRestaurante, Long idPagamento){
        Restaurante restaurante = buscar(idRestaurante);
        FormaPagamento formaPagamento= this.formaPagamentoService.buscar(idPagamento);
        restaurante.removerFormaPagamento(formaPagamento);
    }

    @Transactional
    public void abrir(Long id){
        Restaurante restauranteAtual = buscar(id);
        restauranteAtual.abrir();
    }
    @Transactional
    public void fechar(Long id){
        Restaurante restauranteAtual = buscar(id);
        restauranteAtual.fechar();
    }

}
