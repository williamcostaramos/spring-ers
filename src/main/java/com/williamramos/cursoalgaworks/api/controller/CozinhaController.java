package com.williamramos.cursoalgaworks.api.controller;

import com.williamramos.cursoalgaworks.api.model.CozinhaWrapper;
import com.williamramos.cursoalgaworks.domain.model.Cozinha;
import com.williamramos.cursoalgaworks.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/cozinhas")
public class CozinhaController {
    @Autowired
    private CozinhaRepository repository;

    @GetMapping()
    public List<Cozinha> listar(){
        return repository.listAll();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public CozinhaWrapper listarXML(){
        return  new CozinhaWrapper(repository.listAll());
    }


    @GetMapping("/{id}")
    public Cozinha buscar(@PathVariable Long id){
        return repository.finById(id);
    }

}
