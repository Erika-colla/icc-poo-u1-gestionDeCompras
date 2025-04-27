package ec.edu.ups.poo.clases;
import ec.edu.ups.poo.clases.Producto;

public class DetalleCompra {
    private Producto producto;
    private int cantidad;

    public DetalleCompra(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }
    public double calcularSubtotal(){
        return producto.getPrecioUnitario()*cantidad;
    }
    public double calcularIva(double subtotal){
        return subtotal*0.15;
    }
    public double calcularTotal(){
        double subtotal=calcularSubtotal();
        double iva=calcularIva(subtotal);
        return subtotal+iva;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    @Override
    public String toString() {
        return "DetalleCompra{" +
                "producto=" + producto +
                ", cantidad=" + cantidad +
                ", subtota="+ calcularSubtotal()+
                ", total="+ calcularTotal()+
                "}";
    }
}
