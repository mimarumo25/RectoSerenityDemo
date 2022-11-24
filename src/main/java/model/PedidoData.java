package model;

public class PedidoData {
    private String cliente;
    private String fPedido;
    private String fEnvio;
    private String empleado;
    private String producto;
    private String precio;
    private String cantidad;

    public PedidoData(String cliente, String fPedido, String fEnvio, String empleado, String producto, String precio, String cantidad) {
        this.cliente = cliente;
        this.fPedido = fPedido;
        this.fEnvio = fEnvio;
        this.empleado = empleado;
        this.producto = producto;
        this.precio = precio;
        this.cantidad = cantidad;
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
