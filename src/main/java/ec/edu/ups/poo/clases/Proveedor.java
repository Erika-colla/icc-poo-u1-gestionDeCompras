package ec.edu.ups.poo.clases;

public class Proveedor {
    private String nombre;
    private int ruc;
    private String direccion;
    private int telefono;

    public Proveedor(String nombre, int ruc, String direccion, int telefono) {
        this.nombre = nombre;
        this.ruc = ruc;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public int getRuc() {
        return ruc;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    @Override
    public String toString() {
        return "Proveedor{" +
                "nombre='" + nombre + '\'' +
                ", ruc=" + ruc +
                ", direccion='" + direccion + '\'' +
                ", telefono=" + telefono +
                '}';
    }
}
