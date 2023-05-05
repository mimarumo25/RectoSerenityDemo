package stepdefinitions;


import com.itextpdf.text.DocumentException;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.thucydides.core.environment.SystemEnvironmentVariables;
import net.thucydides.core.reports.TestOutcomeLoader;
import net.thucydides.core.reports.TestOutcomes;
import utils.LectorJson;

import java.io.IOException;
import java.util.Random;


public class Hooks {

    @Before
    public void testBefore() throws DocumentException, IOException {

        System.out.println("Inicio Test");
       // LectorJson lectorJson = new LectorJson();
       // lectorJson.identificaJson();
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
    public void testAfter() throws DocumentException, IOException {
        WebDriverManager.chromedriver().quit();
        System.out.println("Fin Test");

    }
    public static String generarRemisionAleatorio() {
        Random random = new Random();
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();

        // Generar 16 caracteres aleatorios
        for (int i = 0; i < 16; i++) {
            sb.append(letras.charAt(random.nextInt(letras.length())));
        }

        return sb.toString();
    }

}
