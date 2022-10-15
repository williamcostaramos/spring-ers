package com.williamramos.cursoalgaworks.core.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.williamramos.cursoalgaworks.service.StoragefileService;
import com.williamramos.cursoalgaworks.service.impl.LocalStoreFileServiceImpl;
import com.williamramos.cursoalgaworks.service.impl.S3StorageFileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfig {
    @Autowired
    private StoragePropertiers storagePropertiers;

    @Bean
    public AmazonS3 amazonS3() {
        AWSCredentials credentials = new BasicAWSCredentials(storagePropertiers.getS3().getIdChaveAcesso(), storagePropertiers.getS3().getChaveSecreta());
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(storagePropertiers.getS3().getRegiao())
                .build();
    }

    @Bean
    public StoragefileService storagefileService() {
        if (StoragePropertiers.TipoStorage.S3.equals(storagePropertiers.getTipo())) {
            return new S3StorageFileServiceImpl();
        }
        return new LocalStoreFileServiceImpl();
    }
}
