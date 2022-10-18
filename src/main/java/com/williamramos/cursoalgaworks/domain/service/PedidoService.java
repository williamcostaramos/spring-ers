package com.williamramos.cursoalgaworks.domain.service;

import com.williamramos.cursoalgaworks.api.model.filter.PedidoFilter;
import com.williamramos.cursoalgaworks.domain.exception.*;
import com.williamramos.cursoalgaworks.domain.model.*;
import com.williamramos.cursoalgaworks.domain.model.enums.StatusPedido;
import com.williamramos.cursoalgaworks.domain.repository.*;
import com.williamramos.cursoalgaworks.infraestruture.repository.especification.PedidoSpecs;
import com.williamramos.cursoalgaworks.service.EmailSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository repository;
    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private EmailSendService emailSendService;

    public Page<Pedido> listar(PedidoFilter filtro, Pageable pageable) {
        Page<Pedido> pedidos = repository.findAll(PedidoSpecs.filtrar(filtro), pageable);
        if (pedidos.isEmpty()) {
            throw new PedidoNaoEncontradoException();
        }
        return pedidos;
    }

    /**
     * Buscar um unico pedido pelo identificador
     *
     * @param codigo
     * @return Pedido
     */
    public Pedido buscar(String codigo) {
        return repository.findByCodigo(codigo).orElseThrow(() -> new PedidoNaoEncontradoException(codigo));
    }

    public List<Pedido> buscarPedidosRestaurante(Long idRestaurante) {
        List<Pedido> pedidos = repository.buscarPorRestaurante(idRestaurante);
        if (pedidos.isEmpty()) {
            throw new PedidoNaoEncontradoException();
        }
        return pedidos;
    }


    @Transactional
    public Pedido salvar(Pedido pedido) {
        validarPedido(pedido);
        validarItens(pedido);
        pedido.setTaxaFrete(pedido.getRestaurante().getTaxaFrete());
        pedido.calcularValorTotal();
        return repository.save(pedido);
    }

    @Transactional
    public void remover(String codigo, Long idRestaurante) {
        try {
            Pedido pedido = buscar(codigo);
            if (pedido.getRestaurante().getId().equals(idRestaurante)) {
                repository.delete(pedido);
            }
        } catch (EmptyResultDataAccessException e) {
            throw new ProdutoNaoEncontradoException(codigo);
        }

    }

    @Transactional
    public void remover(String codigo) {
        try {
            Pedido pedido = buscar(codigo);
            repository.delete(pedido);
        } catch (EmptyResultDataAccessException e) {
            throw new ProdutoNaoEncontradoException(codigo);
        }
    }

    @Transactional
    public void confirmacaoPedido(String codigo) {
        Pedido pedido = buscar(codigo);
        if (!pedido.getStatusPedido().equals(StatusPedido.CRIADO)) {
            throw new NegocioException(
                    String.format("O status do pedido %s não pode ser alterado para o status %s pois o mesmo foi %s",
                            pedido.getCodigo(), StatusPedido.CONFIRMADO.getDescricao(), pedido.getStatusPedido().getDescricao()));
        }
        pedido.confirmar();
        repository.save(pedido);
    }

    @Transactional
    public void cancelamentoPedido(String codigo) {
        Pedido pedido = buscar(codigo);
        if (pedido.getStatusPedido().equals(StatusPedido.ENTREGUE)) {
            throw new NegocioException(String.format("O status do pedido %s não pode ser alterado para o status %s pois o mesmo foi %s", pedido.getCodigo(), StatusPedido.CANCELADO.getDescricao(), pedido.getStatusPedido().getDescricao()));
        }
        pedido.cancelar();
        repository.save(pedido);
    }

    @Transactional
    public void entregarPedido(String codigo) {
        Pedido pedido = buscar(codigo);
        if (pedido.getStatusPedido().equals(StatusPedido.CANCELADO)) {
            throw new NegocioException(String.format("O status do pedido %s não pode ser alterado para o status %s pois o mesmo foi %s", pedido.getCodigo(), StatusPedido.ENTREGUE.getDescricao(), pedido.getStatusPedido().getDescricao()));
        }
        pedido.entregar();
        repository.save(pedido);
    }

    private void validarPedido(Pedido pedido) {
        Long idRestaurante = pedido.getRestaurante().getId();
        Long idFormaPagamento = pedido.getFormaPagamento().getId();
        Long idCliente = pedido.getCliente().getId();
        Long idCidade = pedido.getEndereco().getCidade().getId();

        Restaurante restaurante = buscarRestaurante(idRestaurante);
        FormaPagamento formaPagamento = buscarFormaPagamento(idFormaPagamento);
        Usuario cliente = buscarCliente(idCliente);
        Cidade cidade = buscarCidade(idCidade);

        pedido.setRestaurante(restaurante);
        pedido.setFormaPagamento(formaPagamento);
        pedido.setCliente(cliente);
        pedido.getEndereco().setCidade(cidade);
        if (!restaurante.aceitaFormaPagamento(formaPagamento)) {
            throw new NegocioException(String.format("Restaurante %s não aceita %s como forma de Pagamento", restaurante.getNome(), formaPagamento.getDescricao()));
        }
    }

    private void validarItens(Pedido pedido) {
        pedido.getItensPedido().forEach(itemPedido -> {
            Produto produto = produtoService.buscar(itemPedido.getProduto().getId(), pedido.getRestaurante().getId());
            itemPedido.setPedido(pedido);
            itemPedido.setProduto(produto);
            itemPedido.setPrecoUnitario(produto.getPreco());
        });
    }

    private Restaurante buscarRestaurante(Long idRestaurante) {
        return restauranteRepository.findById(idRestaurante).orElseThrow(() -> new RestauranteNaoEncontradoException(idRestaurante));
    }

    private FormaPagamento buscarFormaPagamento(Long idFormaPagamento) {
        return formaPagamentoRepository.findById(idFormaPagamento).orElseThrow(() -> new FormaPagamentoNaoEncontradaException(idFormaPagamento));
    }

    private Usuario buscarCliente(Long idCliente) {
        return usuarioRepository.findById(idCliente).orElseThrow(() -> new UsuarioNaoEncontradoException(idCliente));
    }

    private Cidade buscarCidade(Long id) {
        return cidadeRepository.findById(id).orElseThrow(() -> new CidadeNaoEncontradaException(id));
    }

}
