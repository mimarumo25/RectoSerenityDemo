package userinterface;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ClientesPage extends PageObject {

     public static final Target INPUT_ID = Target.the("Input para el campo id")
            .located(By.xpath("//*[@id='Serenity_Demo_Northwind_CustomerDialog9_CustomerID']"));
    public static final Target INPUT_NOMBRE_EMPRESA = Target.the("Input para el campo nombre de empresa")
            .located(By.xpath("//*[@id='Serenity_Demo_Northwind_CustomerDialog9_CompanyName']"));
    public static final Target INPUT_NOMBRE_CONTACTO = Target.the("Input para el campo contacto")
            .located(By.xpath("//*[@id='Serenity_Demo_Northwind_CustomerDialog9_ContactName']"));

    public static final Target INPUT_TITULO_CONTACTO = Target.the("Input para el campo contacto")
            .located(By.xpath("//*[@id='Serenity_Demo_Northwind_CustomerDialog9_ContactTitle']"));

    public static final Target SELECT_REPRESENTANTE = Target.the("select para el campo representante")
            .located(By.xpath("//*[@id='s2id_autogen4']"));

    public static final Target INPUT_DIRECCION = Target.the("Input para direccion")
            .located(By.xpath("//*[@id='Serenity_Demo_Northwind_CustomerDialog9_Address']"));

    public static final Target SELECT_PAIS = Target.the("select para el campo  pais")
            .located(By.id("select2-chosen-5"));

    public static final Target SELECT_PAIS_UL = Target.the("select para el campo  pais")
            .located(By.xpath("//ul[@id='select2-results-5']"));

    public static final Target SELECT_CIUDAD = Target.the("select para el campo  ciudad")
            .located(By.xpath("//*[@id='s2id_Serenity_Demo_Northwind_CustomerDialog9_City']"));
    public static final Target SELECT_CIUDAD_UL = Target.the("buscar ciudad")
            .located(By.xpath("//ul[@id='select2-results-6']"));

    public static final Target INPUT_REGION= Target.the("input para el campo  region")
            .located(By.xpath("//*[@id='Serenity_Demo_Northwind_CustomerDialog9_Region']"));

    public static final Target INPUT_CODIGO_POSTAL= Target.the("input para el campo Código postal")
            .located(By.xpath("//*[@id='Serenity_Demo_Northwind_CustomerDialog9_PostalCode']"));

    public static final Target INPUT_FAX= Target.the("input para el campo fax")
            .located(By.xpath("//*[@id='Serenity_Demo_Northwind_CustomerDialog9_Fax']"));

    public static final Target INPUT_TELEFONO= Target.the("input para el campo telefono")
            .located(By.xpath("//*[@id='Serenity_Demo_Northwind_CustomerDialog9_Phone']"));

    public static final Target DATE_FECHA_ULTIMO_CONTACTO= Target.the("input para el campo Última fecha de contacto")
            .located(By.xpath("//*[@id='Serenity_Demo_Northwind_CustomerDialog9_LastContactDate']"));

    public static final Target SELECT_ULTIMO_CONTACTO_POR= Target.the("input para el campo Último contacto por")
            .located(By.xpath("//div[@id='s2id_Serenity_Demo_Northwind_CustomerDialog9_LastContactedBy']"));

    public static final Target SELECT_ULTIMO_CONTACTO_POR_UL = Target.the(" Selecciona ultimo contacto")
            .located(By.xpath("//ul[@id='select2-results-7']"));

    public static final Target INPUT_EMAIL= Target.the("input para el campo Correo electrónico")
            .located(By.xpath("//*[@id='Serenity_Demo_Northwind_CustomerDialog9_Email']"));

    public static final Target CHECK_BOLETIN= Target.the("Check para el campo Enviar boletín")
            .located(By.xpath("//label[contains(text(),'Send Bulletin')]"));

    public static final Target BUTTON_GUARDAR= Target.the("Boton para el boton guardar")
            .located(By.xpath("//*[@id='Serenity_Demo_Northwind_CustomerDialog9_Toolbar']/div/div/div/div[1]"));


        public static final Target ID_CLIENTES_TABLE= Target.the("Validacion del cliente registrado en el sistema")
            .located(By.xpath("//div[@class='slick-viewport']"));




}

