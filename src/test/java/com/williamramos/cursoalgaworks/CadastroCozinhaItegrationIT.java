package com.williamramos.cursoalgaworks;

import com.williamramos.cursoalgaworks.domain.exception.CozinhaEmUsoException;
import com.williamramos.cursoalgaworks.domain.exception.CozinhaNaoEncontradaException;
import com.williamramos.cursoalgaworks.domain.model.Cozinha;
import com.williamramos.cursoalgaworks.domain.service.CozinhaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CadastroCozinhaItegrationIT {
    @Autowired
    private CozinhaService service;

    @Test
    public void testarCadastroCozinhaSucesso() {
        Cozinha cozinha = new Cozinha();
        cozinha.setNome("La casa de Humbergue");
        cozinha = service.salvar(cozinha);
        assertNotNull(cozinha);
        assertNotNull(cozinha.getId());

    }

    @Test(expected = ConstraintViolationException.class)
    public void testarCadastroCozinhaFalha() {
        Cozinha cozinha = new Cozinha();
        cozinha.setNome(null);
        cozinha = service.salvar(cozinha);

    }

    @Test(expected = CozinhaEmUsoException.class)
    public void testarExcluirCozinhaEmUso(){
        service.remover(1L);
    }
    @Test(expected = CozinhaNaoEncontradaException.class)
    public void testarRemoverCozinhaInesistente(){
        service.remover(98L);
    }
}
