package com.williamramos.cursoalgaworks.api.controller;

import com.williamramos.cursoalgaworks.domain.model.Cliente;
import com.williamramos.cursoalgaworks.domain.model.Usuario;
import com.williamramos.cursoalgaworks.domain.model.enums.TipoUsuario;
import com.williamramos.cursoalgaworks.domain.repository.UsuarioRepository;
import com.williamramos.cursoalgaworks.service.AtivacaoClienteService;
import com.williamramos.cursoalgaworks.service.EmissaoNotaFiscalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "usuarios")
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

    @GetMapping("/salvar")
    public void salvar() {
        Cliente c1 = new Cliente();
        c1.setNome("William");
        c1.setEmail("wulliamcostaramos@gmail.com");
        c1.setTelefone("63 9829687");
        this.ativacaoClienteService.ativar(c1);
        emissaoNotaFiscalService.emitir(c1);
    }
}
