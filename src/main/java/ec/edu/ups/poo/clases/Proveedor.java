package ec.edu.ups.poo.clases;

public class Proveedor {
    private String nombre;
    private int ruc;
    private String direccion;
    private int telefono;

    public Proveedor(int ruc, String direccion) {
        this.nombre = nombre;
        this.ruc = ruc;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRuc() {
        return ruc;
    }

    public void setRuc(int ruc) {
        this.ruc = ruc;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
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

    public int getId() {
        return 0;
    }
}