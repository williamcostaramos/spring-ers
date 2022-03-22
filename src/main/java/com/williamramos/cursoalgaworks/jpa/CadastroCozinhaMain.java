package com.williamramos.cursoalgaworks.jpa;

import com.williamramos.cursoalgaworks.CursoAlgaworksApplication;
import com.williamramos.cursoalgaworks.domain.model.Cozinha;
import com.williamramos.cursoalgaworks.domain.repository.CozinhaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class CadastroCozinhaMain {
    public static void main(String[] args) {
        ApplicationContext context = new SpringApplicationBuilder(CursoAlgaworksApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CozinhaRepository repository = context.getBean(CozinhaRepository.class);
        Cozinha cozinha = new Cozinha();
        cozinha.setId(9L);
        cozinha.setNome("Mexicana");


        cozinha = repository.salvar(cozinha);
        System.out.printf("%d - %s \n", cozinha.getId(), cozinha.getNome());
    }
}
