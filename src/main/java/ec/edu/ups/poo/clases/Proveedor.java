package ec.edu.ups.poo.clases;

public class Proveedor {
    private int id;
    private String nombre;
    private int ruc;
    private String direccion;
    private int telefono;

    public Proveedor(int id, String nombre, int ruc, String direccion, int telefono) {
        this.id = id;
        this.nombre = nombre;
        this.ruc = ruc;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public int getId() {
        return id;
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
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", ruc=" + ruc +
                ", direccion='" + direccion + '\'' +
                ", telefono=" + telefono +
                '}';
    }
}