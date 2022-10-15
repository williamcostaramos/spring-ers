package com.williamramos.cursoalgaworks.service;

import com.williamramos.cursoalgaworks.arquivo.FotoRecuperada;
import com.williamramos.cursoalgaworks.arquivo.NovaFoto;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.util.UUID;

public interface StoragefileService {
    FotoRecuperada recuperar(String nomeArquivo);
    void storage(NovaFoto foto);


    void remover(String nomeArquivo);

    default void substituir(String nomeFotoAntiga, NovaFoto novaFoto) {
        this.storage(novaFoto);
        if (!nomeFotoAntiga.isEmpty()) {
            this.remover(nomeFotoAntiga);
        }
    }

    default String gerarNomeArquivo(String nomeArquivo) {
        return UUID.randomUUID().toString() + "-" + StringUtils.trimAllWhitespace(nomeArquivo).toLowerCase();
    }
}
