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

    private final List<PedidoData> producto;

    public DiligenciarFormularioProductos(List<PedidoData> productos) {
        this.producto = productos;
    }
    public static DiligenciarFormularioProductos llenarProductos(List<PedidoData> productos) {
        return new DiligenciarFormularioProductos(productos);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
       actor.attemptsTo(
                Click.on(SELECT_PRODUCTO),
                SelectUnit.on(SELECT_PRODUCTO_UL, producto.get(0).getProducto()),
                Enter.theValue(producto.get(0).getPrecio()).into(INPUT_PRECIO),
                Enter.theValue(producto.get(0).getCantidad()).into(INPUT_CANTIDA),
                Click.on(BUTTON_GUARDAR_PRODUCTOS),
                Click.on(BUTTON_GUARDAR_PEDIDO),
               WaitUntil.the(LBL_PEDIDO, isVisible()).forNoMoreThan(10).seconds()
        );

    }
}
