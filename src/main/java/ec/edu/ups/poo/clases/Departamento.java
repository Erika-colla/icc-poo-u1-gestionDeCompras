package ec.edu.ups.poo.clases;

public class Departamento {

    private Empleado responsable;

    public Departamento(int empleadoId, String empleadoNombre, String empleadoCargo, String empleadoTelefono) {
        this.responsable = new Empleado(empleadoId, empleadoNombre, empleadoCargo, empleadoTelefono);
    }

    public Empleado getResponsable() {
        return responsable;
    }

}
