package com.williamramos.cursoalgaworks.jpa;

import com.williamramos.cursoalgaworks.CursoAlgaworksApplication;
import com.williamramos.cursoalgaworks.domain.model.Restaurante;
import com.williamramos.cursoalgaworks.domain.repository.RestauranteRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class RestauranteListMain {
    public static void main(String[] args) {
        ApplicationContext context = new SpringApplicationBuilder(CursoAlgaworksApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
        RestauranteRepository repository = context.getBean(RestauranteRepository.class);
        List<Restaurante> restaurantes = repository.listAll();
        for (Restaurante restaurante : restaurantes){
            System.out.printf("Restaurante: %s - Taxa: R$%s,00 - Comida: %s  \n", restaurante.getNome(), restaurante.getTaxaFrete(), restaurante.getCozinha().getNome());
        }
    }
}
