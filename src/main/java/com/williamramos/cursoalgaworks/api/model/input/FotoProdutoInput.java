package com.williamramos.cursoalgaworks.api.model.input;

import com.williamramos.cursoalgaworks.core.annotation.FileContentTypeSize;
import com.williamramos.cursoalgaworks.core.annotation.FileSize;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class FotoProdutoInput {
    @FileSize(max = "1000KB")
    @FileContentTypeSize(allowed = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    @NotNull
    private MultipartFile arquivo;
    @NotBlank
    private String descricao;

    public MultipartFile getArquivo() {
        return arquivo;
    }

    public void setArquivo(MultipartFile arquivo) {
        this.arquivo = arquivo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
