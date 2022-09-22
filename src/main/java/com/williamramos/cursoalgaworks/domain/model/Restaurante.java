package com.williamramos.cursoalgaworks.domain.model;

import com.williamramos.cursoalgaworks.core.annotation.ValorZeroIncluirDescricao;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.*;

@ValorZeroIncluirDescricao(valorField = "taxaFrete", descricaoField = "nome", descricaoObrigatoria = "frete gratis")
@Entity
@Table(name = "tb_restaurante")
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "nome")
    private String nome;


    @Column(name = "taxa_frete")
    private BigDecimal taxaFrete;

    @Column(name = "ativo")
    private Boolean ativo = Boolean.TRUE;

    @Column(name = "aberto")
    private Boolean aberto = Boolean.FALSE;

    @Embedded
    private Endereco endereco;


    @ManyToOne()
    @JoinColumn(name = "cozinha_id")

    @Valid
    private Cozinha cozinha;

    @ManyToMany
    @JoinTable(name = "tb_restaurante_forma_pagamento",
            joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id")
    )
    private Set<FormaPagamento> formaPagamentos = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "tb_restaurante_responsavel",
            joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private Set<Usuario> responsaveis = new HashSet<>();

    @Fetch(FetchMode.JOIN)
    @OneToMany(mappedBy = "restaurante")
    private Set<Produto> produtos = new HashSet<>();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getTaxaFrete() {
        return taxaFrete;
    }

    public void setTaxaFrete(BigDecimal taxaFrete) {
        this.taxaFrete = taxaFrete;
    }

    public Cozinha getCozinha() {
        return cozinha;
    }

    public void setCozinha(Cozinha cozinha) {
        this.cozinha = cozinha;
    }

    public Set<FormaPagamento> getFormaPagamentos() {
        return formaPagamentos;
    }

    public void setFormaPagamentos(Set<FormaPagamento> formaPagamentos) {
        this.formaPagamentos = formaPagamentos;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Set<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(Set<Produto> produtos) {
        this.produtos = produtos;
    }

    public Set<Usuario> getResponsaveis() {
        return responsaveis;
    }

    public void setResponsaveis(Set<Usuario> usuarios) {
        this.responsaveis = usuarios;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
    public void ativar(){
        this.ativo = true;
    }
    public void inativar(){
        this.ativo = false;
    }

    public void adicionarFormaPagamento(FormaPagamento formaPagamento){
        this.formaPagamentos.add(formaPagamento);
    }
    public void removerFormaPagamento(FormaPagamento formaPagamento){
        this.formaPagamentos.remove(formaPagamento);
    }

    public Boolean aceitaFormaPagamento(FormaPagamento formaPagamento){
        return this.formaPagamentos.contains(formaPagamento);
    }
    public void adicionarResponsavel(Usuario responsavel){
        this.responsaveis.add(responsavel);
    }
    public void removerResponsavel(Usuario responsavel){
        this.responsaveis.remove(responsavel);
    }

    public void abrir(){
        this.aberto =true;
    }
    public void fechar(){
        this.aberto=false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurante that = (Restaurante) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public Boolean getAberto() {
        return aberto;
    }

    public void setAberto(Boolean aberto) {
        this.aberto = aberto;
    }
}
