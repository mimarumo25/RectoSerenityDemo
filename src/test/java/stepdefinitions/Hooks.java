package stepdefinitions;



import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.thucydides.core.environment.SystemEnvironmentVariables;
import utils.Utils;

import java.io.IOException;


public class Hooks  {

    @Before
    public void testBefore() throws IOException {
       // Utils utils = new Utils();
       //utils.identificaJson();
        System.out.println("Inicio Test");

        switch (SystemEnvironmentVariables.createEnvironmentVariables().getProperty("webdriver.driver")) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                break;
            case "iexplorer":
                WebDriverManager.iedriver().setup();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                break;
            default:
                WebDriverManager.chromedriver().setup();
                break;
        }
    }

    @After
    public void testAfter() {
        WebDriverManager.chromedriver().quit();
        System.out.println("Fin Test");

    }

}
