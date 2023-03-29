package task;


import interactions.SelectUnit;
import model.PedidoData;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

import static userinterface.PedidosPage.*;
import java.util.List;

public class DiligenciarFormularioPedidos implements Task {

    private final PedidoData pedido;

    public DiligenciarFormularioPedidos(PedidoData pedido) {
        this.pedido = pedido;
    }

    public static DiligenciarFormularioPedidos llenarPedidos(PedidoData pedidos) {
        return new DiligenciarFormularioPedidos(pedidos);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(
            Click.on(CLIENTE),
            SelectUnit.on(SELECT_CLIENTE_UL, pedido.getCliente()),
            Enter.theValue(pedido.getfPedido()).into(DATE_FECHA_PEDIDO),
            Enter.theValue(pedido.getfEnvio()).into(DATE_REQUERIDA_PEDIDO),
            Click.on(SELECT_EMPLEADO),
            SelectUnit.on(SELECT_EMPLEADO_UL, pedido.getEmpleado()),
            Click.on(BUTTON_PRODUCTOS)

    );
    }
}
