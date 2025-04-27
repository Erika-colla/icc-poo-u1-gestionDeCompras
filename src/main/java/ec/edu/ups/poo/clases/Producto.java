package ec.edu.ups.poo.clases;
import ec.edu.ups.poo.enums.UnidadDeMedida;

public abstract class Producto {
    private int id;
    private String nombre;
    private double precioUnitario;
    private UnidadDeMedida medida;

    public Producto(int id, String nombre, double precioUnitario, UnidadDeMedida medida) {
        this.id = id;
        this.nombre = nombre;
        this.precioUnitario = precioUnitario;
        this.medida = medida;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public UnidadDeMedida getMedida() {
        return medida;
    }

    public double calcularDescuento(double descuento) {
        return precioUnitario - (precioUnitario * descuento / 100);
    }

    public String imprimirDetalle() {
        return "ID: " + id + ", Nombre: " + nombre + ", Precio: " + precioUnitario + ", Unidad: " + medida;
    }
    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precioUnitario=" + precioUnitario +
                ", medida=" + medida +
                '}';
    }
}
