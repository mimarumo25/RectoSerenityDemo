package userinterface;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;

@DefaultUrl("https://serenity.is/demo")
public class SerenityPage extends PageObject {
    public static final Target LABEL_MENU_VIENTO_NORTE = Target.the("Label menú viento del norte")
            .located(By.xpath("//span[contains(text(),'Northwind')]"));
    public static final Target LABEL_SUBMENU_CLIENTES = Target.the("Label submenú clientes")
            .located(By.xpath("//*[@id='nav_menu1_2_1']/li[1]/a/span"));
    public static final Target BTN_NUEVO_CLIENTE = Target.the("Boton de nuevo cliente")
            .located(By.xpath("//*[@id='GridDiv']/div[2]/div[2]/div/div/div[1]"));

    public static final Target LABEL_SUBMENU_PEDIDOS = Target.the("Label submenú clientes")
            .located(By.xpath("//span[contains(text(),'Orders')]"));
    public static final Target BTN_NUEVO_PEDIDOS = Target.the("Boton de nuevo cliente")
            .located(By.xpath("//i[@class='fa fa-plus-circle text-green']"));
}
