package com.everis.base;

import com.everis.base.models.Book;
import com.everis.base.models.User;
import com.google.gson.JsonObject;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import net.thucydides.core.annotations.Step;
import org.hamcrest.CoreMatchers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.given;
import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class StoreOrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StoreOrderService.class);
    static private String BASE_URL = "https://petstore.swagger.io/v2/";

    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec;
    private Response response;
    private RequestSpecBuilder builder;
    private RequestSpecification requestSpecification;
    private String bodyPost;

    @Before
    public void init() {

        LOGGER.info(" Inicializa el constructor request ");
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .build();

        LOGGER.info(" Inicializa el constructor response ");
        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
    }

    public void setUrl(String url){
        BASE_URL = url;
        LOGGER.info("URL BASE = " + BASE_URL);
    }

    public void validarStatus(int i) {
        LOGGER.info("Validar Status: " + i);
        assertThat(lastResponse().statusCode(), is(i));
    }

    public void validarPetID(int petId) {
        LOGGER.info("Validando PetID: " + petId);
        assertThat(lastResponse().getBody().path("petId"), equalTo(petId));
    }

    public void validarCantidadOrder(int cantidad) {
        LOGGER.info("Validando Cantidad: " + cantidad);
        assertThat(lastResponse().getBody().path("quantity"), equalTo(cantidad));
    }

    public void validarDataResponse(String campo, int v) {
        LOGGER.info("Campo: " + campo + " , Valor: " + v);
        response = given().spec(requestSpecification).when().post("store/order");
        assertThat(response.body().path(campo), CoreMatchers.equalTo(v));
    }

    public void consultarOrder(int id){
        LOGGER.info("Consultar Pedido: " + id);
        given().
                spec(requestSpec).
                log().all().
                pathParams("orderId", id).
                when().
                get("store/order/{orderId}").
                then().
                spec(responseSpec).
                and().
                log().all();
    }

    public void insertarOrder(int id,int idPet,int cantidad){
        LOGGER.info("Registrar Pedidos: " + id + " - " + idPet + " - " + cantidad);
        RestAssured.baseURI = BASE_URL;
        builder = new RequestSpecBuilder();
        requestSpecification = builder.build();
        JsonObject parametros = new JsonObject();
        parametros.addProperty("id",id);
        parametros.addProperty("petId",idPet);
        parametros.addProperty("quantity",cantidad);
        bodyPost = parametros.toString();
        builder.setBody(bodyPost);
        //
        given().
                header("Content-type", "application/json").
                log().all().
                body(bodyPost).
                when().
                post("store/order").
                then().
                log().all().
                statusCode(200);
    }

}
