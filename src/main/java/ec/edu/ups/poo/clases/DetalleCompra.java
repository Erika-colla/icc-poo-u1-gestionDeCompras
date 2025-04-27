package ec.edu.ups.poo.clases;
import ec.edu.ups.poo.interfaces.Calculable;

public class DetalleCompra implements Calculable {
    private Producto producto;
    private int cantidad;

    public DetalleCompra(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }
    @Override
    public double calcularSubtotal(){
        return producto.getPrecioUnitario()*cantidad;
    }

    @Override
    public double calcularIva(double subtotal){
        return subtotal*0.15;
    }

    @Override
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
