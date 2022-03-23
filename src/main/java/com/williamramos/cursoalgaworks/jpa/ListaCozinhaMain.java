package com.williamramos.cursoalgaworks.jpa;

import com.williamramos.cursoalgaworks.CursoAlgaworksApplication;
import com.williamramos.cursoalgaworks.domain.model.Cozinha;
import com.williamramos.cursoalgaworks.domain.repository.CozinhaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ListaCozinhaMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(CursoAlgaworksApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
        CozinhaRepository repository = applicationContext.getBean(CozinhaRepository.class);
        List<Cozinha> cozinhas = repository.listAll();

        if (!cozinhas.isEmpty()) {
            for (Cozinha cozinha : cozinhas) {
                System.out.printf("Cozinha: %s \n", cozinha.getNome());
            }
        } else {
            System.out.println("Nãp encontrei nada");
        }


        for(int i=cozinhas.size() - 1; i <=cozinhas.size(); i++){
            Cozinha cozinha = cozinhas.get(i);
            repository.remove(cozinha.getId());
        }


    }
}
