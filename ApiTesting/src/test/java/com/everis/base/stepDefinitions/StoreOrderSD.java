package com.everis.base.stepDefinitions;

import com.everis.base.StoreOrderService;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

public class StoreOrderSD {

    @Steps
    StoreOrderService order;

    @Given("el servicio URL {string}")
    public void elServicioURL(String url) {
        order.setUrl(url);
    }

    @When("se consulta el servicio con el {int}")
    public void seConsultaElServicioConElId(int id) {
        order.consultarOrder(id);
    }

    @Then("valida que el ID de la Mascota sea {int}")
    public void validaQueElIDDeLaMascotaSea(int pedId) {
        order.validarPetID(pedId);
    }

    @And("valida que la Cantidad sea {int}")
    public void validaQueLaCantidadSea(int quantity) {
        order.validarCantidadOrder(quantity);
    }

    @And("valida que el Status es {int}")
    public void validaQueElStatusEs(int status) {
        order.validarStatus(status);
    }

    @When("se registra los datos del Pedido: {int}, {int} y {int}")
    public void seRegistraLosDatosDelPedidoIdPetIdYQuantity(int id,int idPet,int quantity) {
        order.insertarOrder(id,idPet,quantity);
    }

    /*
    @Then("valida que {string} sea {int}")
    public void validaQueSea(String campo, int id) {
        order.validarDataResponse(campo,id);
    }*/

}
