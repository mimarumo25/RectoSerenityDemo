package task;


import interactions.SelectUnit;
import model.ClientesData;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Hit;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.Keys;


import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static userinterface.ClientesPage.*;
import static userinterface.PedidosPage.LBL_PEDIDO;


import java.util.List;
public class DiligenciarFormularioClientes implements Task {

    private List<ClientesData> cliente;

    public DiligenciarFormularioClientes(List<ClientesData> clientes) {
        this.cliente = clientes;
    }

    public static DiligenciarFormularioClientes llenarFormulario(List<ClientesData> clientes) {
        return new DiligenciarFormularioClientes(clientes);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(

                Enter.theValue(cliente.get(0).getId()).into(INPUT_ID),
                Enter.theValue(cliente.get(0).getEmpresa()).into(INPUT_NOMBRE_EMPRESA),
                Enter.theValue(cliente.get(0).getnContacto()).into(INPUT_NOMBRE_CONTACTO),
                Enter.theValue(cliente.get(0).gettContacto()).into(INPUT_TITULO_CONTACTO),
                Enter.theValue(cliente.get(0).getRepresentante()).into(SELECT_REPRESENTANTE),
                Hit.the(Keys.ENTER).into(SELECT_REPRESENTANTE),
                Enter.theValue(cliente.get(0).getDireccion()).into(INPUT_DIRECCION),
                Click.on(SELECT_PAIS),
                SelectUnit.on(SELECT_PAIS_UL, cliente.get(0).getPais()),
                Click.on(SELECT_CIUDAD),
                SelectUnit.on(SELECT_CIUDAD_UL, cliente.get(0).getCiudad()),
                Enter.theValue(cliente.get(0).getRegion()).into(INPUT_REGION),
                Enter.theValue(cliente.get(0).getCodigoPostal()).into(INPUT_CODIGO_POSTAL),
                Enter.theValue(cliente.get(0).getTelefono()).into(INPUT_TELEFONO),
                Enter.theValue(cliente.get(0).getFax()).into(INPUT_FAX),
                Enter.theValue(cliente.get(0).getfContacto()).into(DATE_FECHA_ULTIMO_CONTACTO),
                Click.on(SELECT_ULTIMO_CONTACTO_POR),
                SelectUnit.on(SELECT_ULTIMO_CONTACTO_POR_UL,cliente.get(0).getuContactoPor()),
                Enter.theValue(cliente.get(0).getCorreo()).into(INPUT_EMAIL),
                Click.on(CHECK_BOLETIN),
                Scroll.to(BUTTON_GUARDAR),
                Click.on(BUTTON_GUARDAR) ,
                WaitUntil.the(ID_CLIENTES_TABLE, isVisible()).forNoMoreThan(10).seconds()
        );

    }
}
