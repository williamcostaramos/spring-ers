package com.williamramos.cursoalgaworks.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.williamramos.cursoalgaworks.arquivo.FotoRecuperada;
import com.williamramos.cursoalgaworks.arquivo.NovaFoto;
import com.williamramos.cursoalgaworks.core.config.properties.StoragePropertiers;
import com.williamramos.cursoalgaworks.domain.exception.StorageFileException;
import com.williamramos.cursoalgaworks.service.StoragefileService;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;

public class S3StorageFileServiceImpl implements StoragefileService {
    @Autowired
    private AmazonS3 amazonS3;
    @Autowired
    private StoragePropertiers storagePropertiers;

    @Override
    public FotoRecuperada recuperar(String nomeArquivo) {
        String caminhoAquivo = caminhoArquivo(nomeArquivo);
        FotoRecuperada fotoRecuperada = new FotoRecuperada();
        URL url = amazonS3.getUrl(storagePropertiers.getS3().getBucket(), caminhoAquivo);
        fotoRecuperada.setUrl(url.toString());
        return fotoRecuperada;
    }

    @Override
    public void storage(NovaFoto foto) {
        try {
            String bucket = storagePropertiers.getS3().getBucket();
            String caminhoArquivo = caminhoArquivo(foto.getNomeArquivo());

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(foto.getContentType());
            PutObjectRequest arquivo = new PutObjectRequest(bucket, caminhoArquivo, foto.getInputStream(), metadata).withCannedAcl(CannedAccessControlList.PublicRead);
            amazonS3.putObject(arquivo);
        } catch (Exception e) {
            throw new StorageFileException("Não foi Possivel salvar o arquivo na S3", e);
        }


    }

    @Override
    public void remover(String nomeArquivo) {
        try {
            DeleteObjectRequest deleteArquivo = new DeleteObjectRequest(storagePropertiers.getS3().getBucket(), caminhoArquivo(nomeArquivo));
            amazonS3.deleteObject(deleteArquivo);
        } catch (Exception e) {
            throw new StorageFileException("Não foi possivel excluir o arquivo", e);
        }


    }

    private String caminhoArquivo(String arquivo) {
        return String.format("%s/%s", storagePropertiers.getS3().getDiretorioFotos(), arquivo);
    }
}
