package stepdefinitions;


import cucumber.api.java.Before;
import cucumber.api.java.es.*;
import model.ClientesData;
import model.PedidoData;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import questions.ValidarSesion;
import questions.VerificarClintes;
import questions.VerificarPedidos;
import task.*;

import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;

public class SerenityStepDefinitions {
    @Before
    public void setStage() {
        OnStage.setTheStage((new OnlineCast()));
    }

    @Dado("^que miguel quiere iniciar sesion con  \"([^\"]*)\" y \"([^\"]*)\" en serenity\\.is$")
    public void queMiguelQuiereIniciarSesionConYEnSerenityIs(String strUsuario, String strPassword) {
        OnStage.theActorCalled("miguel").wasAbleTo(
                OpenSerenity.thePage(),
                Login.conCredenciales(strUsuario, strPassword)
        );
        OnStage.theActorInTheSpotlight().should(
                GivenWhenThen.seeThat(ValidarSesion.homePage())
        );
    }

    @Dado("^que miguel quiere crear un nuevo cliente$")
    public void queMiguelQuiereCrearUnNuevoCliente() {
        OnStage.theActorInTheSpotlight().attemptsTo(SeleccionarCliente.submenu());
    }

    @Cuando("^miguel ingresa los datos del nuevo cliente y da clic en el boton guardar$")
    public void miguelIngresaLosDatosDelNuevoClienteYDaClicEnElBotonGuardar(List<ClientesData> cliente) {
        OnStage.theActorInTheSpotlight().attemptsTo(DiligenciarFormularioClientes.llenarFormulario(cliente));
    }

    @Entonces("^miguel debera ver el nuevo cliente en la lista general de cliente registrados en el sistema (.*)$")
    public void miguelDeberaVerElNuevoClienteEnLaListaGeneralDeClienteRegistradosEnElSistema(String valida) {
        OnStage.theActorInTheSpotlight().should(
                GivenWhenThen.seeThat(VerificarClintes.existeCliente(valida))
        );
    }

    @Dado("^que miguel quiere crear un nuevo pedido ingresa los datos del pedido$")
    public void queMiguelQuiereCrearUnNuevoPedidoIngresaLosDatosDelPedido(List<PedidoData> data) {
        OnStage.theActorInTheSpotlight().attemptsTo(SeleccionarPedidos.submenuPedidos());
        OnStage.theActorInTheSpotlight().attemptsTo(DiligenciarFormularioPedidos.llenarPedidos(data));
    }

    @Cuando("^miguel ingresa los productos del pedido y da clic en el boton guardarm$")
    public void miguelIngresaLosProductosDelPedidoYDaClicEnElBotonGuardarm(List<PedidoData> producto) {
        OnStage.theActorInTheSpotlight().attemptsTo(DiligenciarFormularioProductos.llenarProductos(producto));
    }

    @Entonces("^miguel debera ver el nuevo pedido en la lista general de pedidos registrados en el sistema$")
    public void miguelDeberaVerElNuevoPedidoEnLaListaGeneralDePedidosRegistradosEnElSistema() {
        OnStage.theActorInTheSpotlight().should(
                GivenWhenThen.seeThat(VerificarPedidos.existePedidos())
        );
    }

}
