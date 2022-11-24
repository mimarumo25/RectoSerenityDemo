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

    private final List<PedidoData> pedido;

    public DiligenciarFormularioPedidos(List<PedidoData> pedidos) {
        this.pedido = pedidos;
    }

    public static DiligenciarFormularioPedidos llenarPedidos(List<PedidoData> pedidos) {
        return new DiligenciarFormularioPedidos(pedidos);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(
            Click.on(CLIENTE),
            SelectUnit.on(SELECT_CLIENTE_UL, pedido.get(0).getCliente()),
            Enter.theValue(pedido.get(0).getfPedido()).into(DATE_FECHA_PEDIDO),
            Enter.theValue(pedido.get(0).getfEnvio()).into(DATE_REQUERIDA_PEDIDO),
            Click.on(SELECT_EMPLEADO),
            SelectUnit.on(SELECT_EMPLEADO_UL, pedido.get(0).getEmpleado()),
            Click.on(BUTTON_PRODUCTOS)

    );
    }
}
