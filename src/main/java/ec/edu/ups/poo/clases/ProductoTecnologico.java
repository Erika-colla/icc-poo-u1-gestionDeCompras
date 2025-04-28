package ec.edu.ups.poo.clases;

public class ProductoTecnologico extends Producto {

    private int garantia;

    public ProductoTecnologico(int id, String nombre, double precioUnitario) {
        super(id, nombre, precioUnitario);
    }

    public int getGarantia() {
        return garantia;
    }

    @Override
    public double calcularDescuento(double porcentaje) {
        return getPrecioUnitario() - (getPrecioUnitario() * porcentaje / 100);
    }

    @Override
    public String imprimirDetalle() {
        return "ID: " + getId() + ", Nombre: " + getNombre() +
                ", Precio: " + getPrecioUnitario() +
                (garantia > 0 ? ", Garantía: " + garantia + " meses" : "");
    }
}