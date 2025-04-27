package ec.edu.ups.poo.clases;

import ec.edu.ups.poo.enums.UnidadDeMedida;

import java.util.GregorianCalendar;

public class ProductoAlimento extends Producto {
    private GregorianCalendar fechaExpiracion;

    public ProductoAlimento(int id, String nombre, double precioUnitario, UnidadDeMedida medida, GregorianCalendar fechaExpiracion) {
        super(id, nombre, precioUnitario, medida);
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
                ", Medida: " + getMedida() +
                ", Fecha de Expiración: " + fechaExpiracion.getTime();
    }
}
