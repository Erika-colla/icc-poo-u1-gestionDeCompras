package ec.edu.ups.poo.clases;

public class ProductoRopa extends Producto {
    private String talla;

    public ProductoRopa(int id, String nombre, double precioUnitario) {
        super(id, nombre, precioUnitario);
        this.talla = talla;
    }


    @Override
    public double calcularDescuento(double porcentaje) {
        return getPrecioUnitario() - (getPrecioUnitario() * porcentaje / 100);
    }

    @Override
    public String imprimirDetalle() {
        return "ID: " + getId() + ", Nombre: " + getNombre() +
                ", Precio: " + getPrecioUnitario() +
                ", Talla: " + talla;
    }
}