package questions;


import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import static userinterface.PedidosPage.LBL_PEDIDO;

public class VerificarPedidos implements Question<Boolean> {

    public static Question<Boolean> existePedidos() {
        return new VerificarPedidos();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return LBL_PEDIDO.resolveFor(actor).isDisplayed();
    }
}
