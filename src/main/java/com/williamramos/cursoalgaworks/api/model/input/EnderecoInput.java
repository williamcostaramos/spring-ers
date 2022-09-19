package com.williamramos.cursoalgaworks.api.model.input;

import com.williamramos.cursoalgaworks.api.model.dto.CidadeResumoDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public class EnderecoInput {
    @NotBlank
    private String cep;
    @NotBlank
    private String logradouro;

    private String complemento;


    private String bairro;

    @Valid
    private CidadeIdInput cidade;

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public CidadeIdInput getCidade() {
        return cidade;
    }

    public void setCidade(CidadeIdInput cidade) {
        this.cidade = cidade;
    }
}
