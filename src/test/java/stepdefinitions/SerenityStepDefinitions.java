package stepdefinitions;




import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.es.*;
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
    public void miguelIngresaLosDatosDelNuevoClienteYDaClicEnElBotonGuardar(DataTable cliente) {
        OnStage.theActorInTheSpotlight().attemptsTo(DiligenciarFormularioClientes.llenarFormulario(ClientesData.setData(cliente).get(0)));
    }

    @Entonces("^miguel debera ver el nuevo cliente en la lista general de cliente registrados en el sistema (.*)$")
    public void miguelDeberaVerElNuevoClienteEnLaListaGeneralDeClienteRegistradosEnElSistema(String valida) {
        OnStage.theActorInTheSpotlight().should(
                GivenWhenThen.seeThat(VerificarClintes.existeCliente(valida))
        );
    }

    @Dado("^que miguel quiere crear un nuevo pedido ingresa los datos del pedido$")
    public void queMiguelQuiereCrearUnNuevoPedidoIngresaLosDatosDelPedido(DataTable data) {
        OnStage.theActorInTheSpotlight().attemptsTo(SeleccionarPedidos.submenuPedidos());
        OnStage.theActorInTheSpotlight().attemptsTo(DiligenciarFormularioPedidos.llenarPedidos(PedidoData.setData(data).get(0)));
    }

    @Cuando("^miguel ingresa los productos del pedido y da clic en el boton guardarm$")
    public void miguelIngresaLosProductosDelPedidoYDaClicEnElBotonGuardarm(DataTable producto ) {
        OnStage.theActorInTheSpotlight().attemptsTo(DiligenciarFormularioProductos.llenarProductos(PedidoData.setData(producto).get(0)));
    }

    @Entonces("^miguel debera ver el nuevo pedido en la lista general de pedidos registrados en el sistema$")
    public void miguelDeberaVerElNuevoPedidoEnLaListaGeneralDePedidosRegistradosEnElSistema() {
        OnStage.theActorInTheSpotlight().should(
                GivenWhenThen.seeThat(VerificarPedidos.existePedidos())
        );
    }

}
