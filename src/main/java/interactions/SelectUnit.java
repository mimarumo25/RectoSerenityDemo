package interactions;


import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;


public class SelectUnit implements Interaction {

    private final Target element;
    private final String name;


    public SelectUnit(Target element, String name) {
        this.element = element;
        this.name = name;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        WebElement listLocation = element.resolveFor(actor);
        List<WebElement> options = listLocation.findElements(By.tagName("li"));
        for (WebElement i : options) {
            if (i.getText().contains(name)){
                i.click();
                break;
            }
        }

    }

    public static SelectUnit on(Target element, String bussinessName) {
        return Instrumented.instanceOf(SelectUnit.class).withProperties(element, bussinessName);
    }
}
