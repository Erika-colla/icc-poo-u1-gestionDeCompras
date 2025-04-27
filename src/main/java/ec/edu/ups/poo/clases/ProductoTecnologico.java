package ec.edu.ups.poo.clases;

import ec.edu.ups.poo.enums.UnidadDeMedida;

public class ProductoTecnologico extends Producto {

    private int garantia;

    public ProductoTecnologico(int id, String nombre, double precioUnitario, UnidadDeMedida medida) {
        super(id, nombre, precioUnitario, medida);
    }


    public int getGarantia() {
        return garantia;
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
                ", Garantía: " + garantia;
    }

}
