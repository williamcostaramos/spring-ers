package com.williamramos.cursoalgaworks.service.impl;

import com.williamramos.cursoalgaworks.arquivo.FotoRecuperada;
import com.williamramos.cursoalgaworks.arquivo.NovaFoto;
import com.williamramos.cursoalgaworks.core.config.StoragePropertiers;
import com.williamramos.cursoalgaworks.domain.exception.StorageFileException;
import com.williamramos.cursoalgaworks.service.StoragefileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class LocalStoreFileServiceImpl implements StoragefileService {
    @Autowired
    private StoragePropertiers storagePropertiers;

    @Override
    public FotoRecuperada recuperar(String nomeArquivo) {
        try {
            Path arquivo = getArquivoPath(nomeArquivo);
            FotoRecuperada fotoRecuperada = new FotoRecuperada();
            fotoRecuperada.setInputStream(Files.newInputStream(arquivo));
            return fotoRecuperada;
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
        return storagePropertiers.getLocal().getdiretorioFotos().resolve(Paths.get(nomeArquivo));
    }
}
