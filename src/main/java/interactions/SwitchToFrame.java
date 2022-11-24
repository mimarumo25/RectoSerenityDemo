package interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

import java.util.Iterator;
import java.util.Set;

public class SwitchToFrame implements Interaction {


    @Override
    public <T extends Actor> void performAs(T actor) {

        Set<String> handles = BrowseTheWeb.as(actor).getDriver().getWindowHandles();
        Iterator<String> itr = handles.iterator();
        Object firstHandle = itr.next();
        Object lastHandle = firstHandle;
        while (itr.hasNext()) {
            lastHandle = itr.next();
        }

        BrowseTheWeb.as(actor).getDriver().switchTo().window(lastHandle.toString());

    }

    public static SwitchToFrame onThePage() {
        return Tasks.instrumented(SwitchToFrame.class);
    }
}
