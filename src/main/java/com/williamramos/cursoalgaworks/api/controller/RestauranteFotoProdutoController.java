package com.williamramos.cursoalgaworks.api.controller;

import com.williamramos.cursoalgaworks.api.converter.Converter;
import com.williamramos.cursoalgaworks.api.model.dto.FotoProdutoDTO;
import com.williamramos.cursoalgaworks.api.model.input.FotoProdutoInput;
import com.williamramos.cursoalgaworks.arquivo.FotoRecuperada;
import com.williamramos.cursoalgaworks.arquivo.NovaFoto;
import com.williamramos.cursoalgaworks.domain.exception.FotoNaoEncontradaException;
import com.williamramos.cursoalgaworks.domain.exception.StorageFileException;
import com.williamramos.cursoalgaworks.domain.model.FotoProduto;
import com.williamramos.cursoalgaworks.domain.model.Produto;
import com.williamramos.cursoalgaworks.domain.service.FotoProdutoService;
import com.williamramos.cursoalgaworks.domain.service.ProdutoService;
import com.williamramos.cursoalgaworks.service.StoragefileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
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
            foto.setNomeArquivo(storagefileService.gerarNomeArquivo(file.getOriginalFilename()));
            foto.setDescricao(arquivo.getDescricao());
            foto.setContentType(file.getContentType());
            foto.setTamanho((double) file.getSize());

            NovaFoto novaFoto = new NovaFoto();
            novaFoto.setInputStream(file.getInputStream());
            novaFoto.setNomeArquivo(foto.getNomeArquivo());
            novaFoto.setContentType(foto.getContentType());
            foto = fotoProdutoService.salvar(foto);
            storagefileService.storage(novaFoto);
            return ResponseEntity.ok(converter.toDTO(foto));

        } catch (IOException | StorageFileException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public FotoProdutoDTO buscar(@PathVariable("idProduto") Long idProduto) {
        FotoProduto fotoProduto = fotoProdutoService.findById(idProduto);
        return converter.toDTO(fotoProduto);
    }

    @GetMapping()
    public ResponseEntity<?> downloadfoto(@PathVariable("idProduto") Long idProduto, @RequestHeader("accept") String acceptHeader) throws HttpMediaTypeNotAcceptableException {
        FotoProduto fotoProduto = fotoProdutoService.findById(idProduto);
        MediaType mediaTypeFoto = MediaType.parseMediaType(fotoProduto.getContentType());
        List<MediaType> mediaTypesAceitas = MediaType.parseMediaTypes(acceptHeader);
        validarMediaTypeFile(mediaTypeFoto, mediaTypesAceitas);
        FotoRecuperada fotoRecuperada = storagefileService.recuperar(fotoProduto.getNomeArquivo());
        if(fotoRecuperada.hasUrl()){
            return  ResponseEntity.status(HttpStatus.FOUND).header(HttpHeaders.LOCATION, fotoRecuperada.getUrl()).build();
        }
        return ResponseEntity.ok().contentType(mediaTypeFoto).body(new InputStreamResource(fotoRecuperada.getInputStream())) ;
    }

    @DeleteMapping()
    public void remover(@PathVariable("idProduto") Long idProduto) {
        FotoProduto fotoProduto = fotoProdutoService.findById(idProduto);
        if (fotoProduto != null) {
            storagefileService.remover(fotoProduto.getNomeArquivo());
            fotoProdutoService.remove(idProduto);
        }
    }

    private void validarMediaTypeFile(MediaType mediaTypeFoto, List<MediaType> mediaTypeAceitas) throws HttpMediaTypeNotAcceptableException {
        boolean mediaTypeAceita = mediaTypeAceitas.stream().anyMatch(mediaType -> mediaType.isCompatibleWith(mediaTypeFoto));
        if (!mediaTypeAceita) {
            throw new HttpMediaTypeNotAcceptableException(mediaTypeAceitas);
        }
    }
}
