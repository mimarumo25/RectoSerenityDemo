package stepdefinitions;


import cucumber.api.java.After;
import cucumber.api.java.Before;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.thucydides.core.util.SystemEnvironmentVariables;


public class Hooks {

    @Before
    public void testBefore(){
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
    public void testAfter(){
        WebDriverManager.chromedriver().quit();

        System.out.println("Fin Test");
    }
}
