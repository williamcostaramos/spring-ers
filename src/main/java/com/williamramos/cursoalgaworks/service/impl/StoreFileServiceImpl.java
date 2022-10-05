package com.williamramos.cursoalgaworks.service.impl;

import com.williamramos.cursoalgaworks.arquivo.NovaFoto;
import com.williamramos.cursoalgaworks.domain.exception.StorageFileException;
import com.williamramos.cursoalgaworks.service.StoragefileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
@Service
public class StoreFileServiceImpl implements StoragefileService {
    @Value("${algafood.storage.foto}")
    private Path pasta;

    @Override
    public void storage(NovaFoto foto) {
        try {
            Path arquivo = getArquivoPath(this.gerarNomeArquivo(foto.getNomeArquivo()));
            FileCopyUtils.copy(foto.getInputStream(), Files.newOutputStream(arquivo));
        } catch (IOException e) {
            throw new StorageFileException("Nao foi possivel Armazenar o arquivo ",e);
        }

    }
    private Path getArquivoPath(String nomeArquivo){
        return pasta.resolve(Paths.get(nomeArquivo));
    }
}
