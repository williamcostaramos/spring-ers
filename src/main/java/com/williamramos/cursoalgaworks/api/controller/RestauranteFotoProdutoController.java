package com.williamramos.cursoalgaworks.api.controller;

import com.williamramos.cursoalgaworks.api.converter.Converter;
import com.williamramos.cursoalgaworks.api.model.dto.FotoProdutoDTO;
import com.williamramos.cursoalgaworks.api.model.input.FotoProdutoInput;
import com.williamramos.cursoalgaworks.arquivo.NovaFoto;
import com.williamramos.cursoalgaworks.domain.exception.StorageFileException;
import com.williamramos.cursoalgaworks.domain.model.FotoProduto;
import com.williamramos.cursoalgaworks.domain.model.Produto;
import com.williamramos.cursoalgaworks.domain.service.FotoProdutoService;
import com.williamramos.cursoalgaworks.domain.service.ProdutoService;
import com.williamramos.cursoalgaworks.service.StoragefileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/restaurantes/{idRestaurante}/produtos/{idProduto}/foto")
public class RestauranteFotoProdutoController {
    @Autowired
    Converter<FotoProduto, FotoProdutoDTO> converter;
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private FotoProdutoService fotoProdutoService;

    @Autowired
    private StoragefileService storagefileService;

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FotoProdutoDTO> atualizarFoto(@PathVariable Long idRestaurante, @PathVariable Long idProduto, @Valid FotoProdutoInput arquivo) {

        try {
            Produto produto = produtoService.buscar(idProduto, idRestaurante);
            FotoProduto foto = new FotoProduto();
            MultipartFile file = arquivo.getArquivo();
            foto.setProduto(produto);
            foto.setNomeArquivo(file.getOriginalFilename());
            foto.setDescricao(arquivo.getDescricao());
            foto.setContentType(file.getContentType());
            foto.setTamanho(file.getSize());

            NovaFoto novaFoto = new NovaFoto();
            novaFoto.setInputStream(file.getInputStream());
            novaFoto.setNomeArquivo(foto.getNomeArquivo());
            foto = fotoProdutoService.salvar(foto);
            storagefileService.storage(novaFoto);

            return ResponseEntity.ok(converter.toDTO(foto));

        } catch (IOException | StorageFileException e) {
            throw new RuntimeException(e);
        }


    }

}
