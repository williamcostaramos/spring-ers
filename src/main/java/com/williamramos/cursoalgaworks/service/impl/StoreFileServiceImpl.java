package com.williamramos.cursoalgaworks.service.impl;

import com.williamramos.cursoalgaworks.arquivo.NovaFoto;
import com.williamramos.cursoalgaworks.domain.exception.StorageFileException;
import com.williamramos.cursoalgaworks.service.StoragefileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class StoreFileServiceImpl implements StoragefileService {
    @Value("${algafood.storage.foto}")
    private Path pasta;

    @Override
    public InputStream recuperar(String nomeArquivo) {
        try {
            Path arquivo = getArquivoPath(nomeArquivo);
            return Files.newInputStream(arquivo);
        } catch (IOException e) {
            throw new StorageFileException("Nao foi possivel Recuperar o arquivo ", e);
        }

    }

    @Override
    public void storage(NovaFoto foto) {
        try {
            Path arquivo = getArquivoPath(foto.getNomeArquivo());
            FileCopyUtils.copy(foto.getInputStream(), Files.newOutputStream(arquivo));
        } catch (IOException e) {
            throw new StorageFileException("Nao foi possivel Armazenar o arquivo ", e);
        }

    }

    @Override
    public void remover(String nomeArquivo) {
        try {
            Path arquivo = getArquivoPath(nomeArquivo);
            Files.deleteIfExists(arquivo);
        } catch (IOException e) {
            throw new StorageFileException("Nao foi possivel excluir o arquivo ", e);

        }
    }

    private Path getArquivoPath(String nomeArquivo) {
        return pasta.resolve(Paths.get(nomeArquivo));
    }
}
