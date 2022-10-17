package com.williamramos.cursoalgaworks.core.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
@ConfigurationProperties("algafood.storage")
public class StoragePropertiers {
    private Local local = new Local();
    private S3 s3= new S3();
    private TipoStorage tipo= TipoStorage.LOCAL;


    public TipoStorage getTipo() {
        return tipo;
    }

    public void setTipo(TipoStorage tipo) {
        this.tipo = tipo;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    public S3 getS3() {
        return s3;
    }

    public void setS3(S3 s3) {
        this.s3 = s3;
    }


    public enum  TipoStorage{
        LOCAL,
        S3
    }

    public class  Local{
        private Path diretorioFotos;

        public Path getdiretorioFotos() {
            return diretorioFotos;
        }

        public void setdiretorioFotos(Path foto) {
            this.diretorioFotos = foto;
        }
    }
    public class S3{
        private String idChaveAcesso;
        private String chaveSecreta;
        private String bucket;
        private String regiao;
        private String diretorioFotos;

        public String getIdChaveAcesso() {
            return idChaveAcesso;
        }

        public void setIdChaveAcesso(String idChaveAcesso) {
            this.idChaveAcesso = idChaveAcesso;
        }

        public String getChaveSecreta() {
            return chaveSecreta;
        }

        public void setChaveSecreta(String chaveSecreta) {
            this.chaveSecreta = chaveSecreta;
        }

        public String getBucket() {
            return bucket;
        }

        public void setBucket(String bucket) {
            this.bucket = bucket;
        }

        public String getRegiao() {
            return regiao;
        }

        public void setRegiao(String regiao) {
            this.regiao = regiao;
        }

        public String getDiretorioFotos() {
            return diretorioFotos;
        }

        public void setDiretorioFotos(String diretorioFotos) {
            this.diretorioFotos = diretorioFotos;
        }
    }
}
