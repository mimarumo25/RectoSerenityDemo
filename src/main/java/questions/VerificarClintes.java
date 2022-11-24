package questions;


import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static userinterface.ClientesPage.ID_CLIENTES_TABLE;



public class VerificarClintes implements Question<Boolean> {
    private String respuesta;

    public VerificarClintes(String respuesta) {
        this.respuesta = respuesta;
    }

    public static Question<Boolean> existeCliente(String respuesta) {

        return new VerificarClintes(respuesta);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        boolean resultado = false;
        WebElement listLocation = ID_CLIENTES_TABLE.resolveFor(actor);
        List<WebElement> options = listLocation.findElements(By.tagName("a"));
        for (WebElement i : options) {
            if (i.getText().contains(respuesta)) {
                resultado = true;
                break;
            }
        }
        return resultado;
    }
}
