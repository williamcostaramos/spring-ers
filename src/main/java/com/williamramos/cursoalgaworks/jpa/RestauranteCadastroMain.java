package com.williamramos.cursoalgaworks.jpa;

import com.williamramos.cursoalgaworks.CursoAlgaworksApplication;
import com.williamramos.cursoalgaworks.domain.model.Restaurante;
import com.williamramos.cursoalgaworks.domain.repository.RestauranteRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;

public class RestauranteCadastroMain {
    public static void main(String[] args) {
        ApplicationContext context = new SpringApplicationBuilder(CursoAlgaworksApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        Restaurante restaurante = new Restaurante();
        restaurante.setNome("Catina Bom Sabor");
        restaurante.setTaxaFrete(new BigDecimal(10));
        RestauranteRepository repository =context.getBean(RestauranteRepository.class);

        repository.salvar(restaurante);
    }
}
