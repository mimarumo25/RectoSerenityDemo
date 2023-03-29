package model;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PedidoData {
    private String cliente;
    private String fPedido;
    private String fEnvio;
    private String empleado;
    private String producto;
    private String precio;
    private String cantidad;


    public static List<PedidoData> setData(DataTable dataTable) {
        List<PedidoData> dates = new ArrayList<>();
        List<Map<String, String>> mapInfo = dataTable.asMaps();
        for (Map<String, String> map : mapInfo) {
            dates.add(new ObjectMapper().convertValue(map, PedidoData.class));
        }
        return dates;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getfPedido() {
        return fPedido;
    }

    public void setfPedido(String fPedido) {
        this.fPedido = fPedido;
    }

    public String getfEnvio() {
        return fEnvio;
    }

    public void setfEnvio(String fEnvio) {
        this.fEnvio = fEnvio;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }
}
