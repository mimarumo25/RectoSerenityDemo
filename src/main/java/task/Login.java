package task;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import userinterface.SerenityLoginPage;

public class Login implements Task {
    private String strUsuario;
    private String strPassword;

    public Login(String strUsuario, String strPassword) {
        this.strUsuario = strUsuario;
        this.strPassword = strPassword;
    }

    public static Login conCredenciales(String strUsuario, String strPassword) {
        return Tasks.instrumented(Login.class,strUsuario,strPassword);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(strUsuario).into(SerenityLoginPage.LOGIN_INPUT_USERNAME),
                Enter.theValue(strPassword).into(SerenityLoginPage.LOGIN_INPUT_PASSWORD),
                Click.on(SerenityLoginPage.LOGIN_BUTTON_SINGIN)
        );

    }
}
