package userinterface;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class PedidosPage extends PageObject {
    public static final Target CLIENTE = Target.the("Select Cliente pedido")
            .located(By.id("s2id_Serenity_Demo_Northwind_OrderDialog15_CustomerID"));
    public static final Target SELECT_CLIENTE_UL = Target.the("Lista de clientes pedidos")
            .located(By.xpath("//ul[@id='select2-results-8']"));
    public static final Target DATE_FECHA_PEDIDO = Target.the("Fecha del pedido")
            .located(By.id("Serenity_Demo_Northwind_OrderDialog15_OrderDate"));
    public static final Target DATE_REQUERIDA_PEDIDO = Target.the("Fecha Requerida del pedido")
            .located(By.id("Serenity_Demo_Northwind_OrderDialog15_RequiredDate"));
    public static final Target SELECT_EMPLEADO = Target.the("Seleciona el empleado")
            .located(By.id("s2id_Serenity_Demo_Northwind_OrderDialog15_EmployeeID"));
    public static final Target SELECT_EMPLEADO_UL = Target.the("Lista de Empleados")
            .located(By.xpath("//ul[@id='select2-results-9']"));
    public static final Target BUTTON_PRODUCTOS = Target.the("Boton para agregar productos")
            .located(By.xpath("//span[contains(text(),'New Order Detail')]"));
    public static final Target SELECT_PRODUCTO = Target.the("Select del productos")
            .located(By.xpath("//*[@id='s2id_Serenity_Demo_Northwind_OrderDetailDialog33_ProductID']/a"));
    public static final Target SELECT_PRODUCTO_UL = Target.the("Seleciona el Producto")
            .located(By.xpath("//*[@class='select2-drop select2-display-none select2-with-searchbox select2-drop-active']/following::ul/li/parent::ul"));
    public static final Target INPUT_PRECIO = Target.the("Precio del Producto")
            .located(By.xpath("//*[@class=\"editor s-Serenity-DecimalEditor s-DecimalEditor decimalQ required\"]"));
    public static final Target INPUT_CANTIDA = Target.the("Cantidad del Producto")
            .located(By.xpath("//*[@name='Quantity']"));
    public static final Target BUTTON_GUARDAR_PRODUCTOS = Target.the("Boton guardar Producto")
            .located(By.xpath("//span[@class='button-inner' and contains(text(), 'Save')]/following::i[@class='fa fa-check-circle text-purple']"));
    public static final Target BUTTON_GUARDAR_PEDIDO = Target.the("Boton guardar Pedido")
            .located(By.xpath("//*[@id='Serenity_Demo_Northwind_OrderDialog15_Toolbar']/div/div/div/div[1]"));
    public static final Target LBL_PEDIDO = Target.the("Validar pedido")
            .located(By.xpath("//span[contains(text(),' New Order')]"));

}
