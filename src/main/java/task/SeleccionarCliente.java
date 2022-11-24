package task;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import  userinterface.ClientesPage;
import userinterface.SerenityPage;

public class SeleccionarCliente implements Task {
    public static SeleccionarCliente submenu() {
        return Tasks.instrumented(SeleccionarCliente.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(SerenityPage.LABEL_MENU_VIENTO_NORTE),
                Click.on(SerenityPage.LABEL_SUBMENU_CLIENTES),
                Click.on(SerenityPage.BTN_NUEVO_CLIENTE)
        );

    }
}
