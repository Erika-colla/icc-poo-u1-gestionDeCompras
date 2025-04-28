package ec.edu.ups.poo.clases;

import java.util.GregorianCalendar;

public class ProductoAlimento extends Producto {
    private GregorianCalendar fechaExpiracion;

    public ProductoAlimento(int id, String nombre, double precioUnitario) {
        super(id, nombre, precioUnitario);
        this.fechaExpiracion = fechaExpiracion;
    }

    @Override
    public double calcularDescuento(double porcentaje) {
        return getPrecioUnitario() - (getPrecioUnitario() * porcentaje / 100);
    }

    @Override
    public String imprimirDetalle() {
        return "ID: " + getId() + ", Nombre: " + getNombre() +
                ", Precio: " + getPrecioUnitario() +
                ", Fecha de Expiración: " + fechaExpiracion.getTime();
    }
}