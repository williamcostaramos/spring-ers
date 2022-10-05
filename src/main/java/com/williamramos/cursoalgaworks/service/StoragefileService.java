package com.williamramos.cursoalgaworks.service;

import com.williamramos.cursoalgaworks.arquivo.NovaFoto;

import java.util.UUID;

public interface StoragefileService {
    void storage(NovaFoto foto);

    default String gerarNomeArquivo(String nomeArquivo){
        return UUID.randomUUID().toString() +"-"+nomeArquivo;
    }
}
