package interactions;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;

import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.WebDriver;

import java.util.Set;

public class SwitchToNewTab implements Interaction {

    @Override
    public <T extends Actor> void performAs(T actor) {


        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        String currHandle = driver.getWindowHandle();
        Set<String> allHandles = driver.getWindowHandles();

        for (String handle : allHandles) {
            if (!currHandle.contentEquals(handle)) {
                driver.switchTo().window(handle);
                break;
            }

        }

    }

    public static SwitchToNewTab change() {

        return Instrumented.instanceOf(SwitchToNewTab.class).withProperties();
    }
}
