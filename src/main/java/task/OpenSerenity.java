package task;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Open;
import userinterface.SerenityPage;

public class OpenSerenity implements Task {
    private SerenityPage serenityPage;

    public static OpenSerenity thePage() {
        return Tasks.instrumented(OpenSerenity.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Open.browserOn(serenityPage));
    }
}
