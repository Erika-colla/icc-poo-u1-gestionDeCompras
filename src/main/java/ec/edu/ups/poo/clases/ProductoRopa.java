package ec.edu.ups.poo.clases;
import ec.edu.ups.poo.enums.UnidadDeMedida;

public class ProductoRopa extends Producto {
    private String talla;

    public ProductoRopa(int id, String nombre, double precioUnitario, UnidadDeMedida medida, String talla) {
        super(id, nombre, precioUnitario, medida);
        this.talla = talla;
    }

    @Override
    public double calcularDescuento(double porcentaje) {
        return getPrecioUnitario()-(getPrecioUnitario()*porcentaje/100);
    }

    @Override
    public String imprimirDetalle() {
        return "ID: " + getId() + ", Nombre: " + getNombre() +
                ", Precio: " + getPrecioUnitario() +
                ", Medida: " + getMedida() +
                ", Talla: " + talla;
    }

}
