package ec.edu.ups.poo.clases;

public class Departamento {

    private Empleado responsable;

    public Departamento(Empleado responsable) {
        this.responsable = responsable;
    }

    public Empleado getResponsable() {
        return responsable;
    }

}