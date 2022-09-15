package com.williamramos.cursoalgaworks;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CadastroCozinhaApiIT {
    @LocalServerPort
    private int port;

    @Before
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/api/cozinhas";
    }

    /**
     * Valida o retorno do status http
     */
    @Test
    public void testarRetornoStatus200QuandoConsultarCozinha() {
        RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .statusCode(HttpStatus.OK.value());
    }


    /**
     * Valida corpo da requisicao, atende aos seguintes critérios informados;
     */

    @Test
    public void testarEseCadastroCozinhaApi(){
        RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("{\"nome\": \"Jurubeba 12124\"}")
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

}
