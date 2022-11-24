package userinterface;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class SerenityLoginPage extends PageObject {
    public static final Target LOGIN_INPUT_USERNAME = Target.the("Campo para ingresar el username")
            .located(By.xpath("//*[@id='LoginPanel0_Username']"));
    public static final Target LOGIN_INPUT_PASSWORD = Target.the("Campo para ingresar el passwword")
            .located(By.xpath("//*[@id='LoginPanel0_Password']"));
    public static final Target LOGIN_BUTTON_SINGIN = Target.the("Botón de login")
            .located(By.xpath("//*[@id='LoginPanel0_LoginButton']"));
    public static final Target HOME_LOGO = Target.the("Dashboard en la pantalla principal")
            .located(By.xpath("//h1[contains(text(),'Dashboard')]"));

}
