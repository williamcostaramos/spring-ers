package com.williamramos.cursoalgaworks.api.controller;

import com.williamramos.cursoalgaworks.api.model.Endereco;
import com.williamramos.cursoalgaworks.domain.model.Cliente;
import com.williamramos.cursoalgaworks.domain.model.Usuario;
import com.williamramos.cursoalgaworks.domain.model.enums.TipoUsuario;
import com.williamramos.cursoalgaworks.domain.repository.UsuarioRepository;
import com.williamramos.cursoalgaworks.service.AtivacaoClienteService;
import com.williamramos.cursoalgaworks.service.EmissaoNotaFiscalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository repository;
    private AtivacaoClienteService ativacaoClienteService;
    @Autowired
    private EmissaoNotaFiscalService emissaoNotaFiscalService;



    public UsuarioController(AtivacaoClienteService ativacaoClienteService) {
        this.ativacaoClienteService = ativacaoClienteService;
        System.out.println("UsuarioContoller"+ ativacaoClienteService);
    }

    private Usuario usuario;

    @PostMapping
    public void salvar(@RequestBody Usuario usuario) {
        Usuario user = new Usuario();
        user = usuario;
        System.out.printf(user.toString());
    }
    @GetMapping("/cep/{cep}")
    public List<Object> listarCep(@PathVariable Long cep){
        String uri = "https://viacep.com.br/ws/"+cep+"/json/";
        RestTemplate restTemplate = new RestTemplate();
        Endereco result = restTemplate.getForObject(uri,Endereco.class);
        return Arrays.asList(result);
    }
}
