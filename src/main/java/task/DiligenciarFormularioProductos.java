package task;

import interactions.SelectUnit;
import model.PedidoData;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.util.List;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static userinterface.PedidosPage.*;


public class DiligenciarFormularioProductos implements Task {

    private final PedidoData  producto;

    public DiligenciarFormularioProductos(PedidoData producto) {
        this.producto = producto;
    }

    public static DiligenciarFormularioProductos llenarProductos(PedidoData productos) {
        return new DiligenciarFormularioProductos(productos);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
       actor.attemptsTo(
                Click.on(SELECT_PRODUCTO),
                SelectUnit.on(SELECT_PRODUCTO_UL, producto.getProducto()),
                Enter.theValue(producto.getPrecio()).into(INPUT_PRECIO),
                Enter.theValue(producto.getCantidad()).into(INPUT_CANTIDA),
                Click.on(BUTTON_GUARDAR_PRODUCTOS),
                Click.on(BUTTON_GUARDAR_PEDIDO),
               WaitUntil.the(LBL_PEDIDO, isVisible()).forNoMoreThan(10).seconds()
        );

    }
}
