package com.williamramos.cursoalgaworks.api.controller;

import com.williamramos.cursoalgaworks.domain.model.Restaurante;
import com.williamramos.cursoalgaworks.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/restaurantes")
public class RestauranteController {
    @Autowired
    private RestauranteRepository repository;

    @GetMapping()
    public List<Restaurante> listar(){
        return repository.listAll();
    }

}
