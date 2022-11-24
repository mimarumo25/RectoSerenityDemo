package interactions;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ManejoListas implements Interaction {
    private final Target element;
    private final String dato;
    public ManejoListas(Target element, String dato) {
        this.element = element;
        this.dato = dato;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebElement listLocation = element.resolveFor(actor);
        List<WebElement> options = listLocation.findElements(By.tagName("a"));
        for (WebElement i : options) {
            if (i.getText().contains(dato)){
                i.click();
                break;
            }
        }

    }

    public static ManejoListas on(Target element, String dato) {
        return Instrumented.instanceOf(ManejoListas.class).withProperties(element, dato);
    }
}
