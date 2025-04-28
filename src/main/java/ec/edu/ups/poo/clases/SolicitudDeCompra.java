package ec.edu.ups.poo.clases;

import ec.edu.ups.poo.enums.EstadoSolicitud;


import java.util.GregorianCalendar;
import java.util.List;

public class SolicitudDeCompra {
    private int id;
    private GregorianCalendar fechaEmision;
    private String numero;
    private Departamento departamento;
    private EstadoSolicitud estado;
    private List<DetalleCompra> detalles;

    public SolicitudDeCompra(int id, List detalles, EstadoSolicitud estado, Departamento departamento, String numero, GregorianCalendar fechaEmision) {
        this.id = id;
        this.detalles = detalles;
        this.estado = estado;
        this.departamento = departamento;
        this.numero = numero;
        this.fechaEmision = fechaEmision;
    }

    public int getId() {
        return id;
    }

    public GregorianCalendar getFechaEmision() {
        return fechaEmision;
    }

    public String getNumero() {
        return numero;
    }

    public EstadoSolicitud getEstado() {
        return estado;
    }

    public List getList() {
        return detalles;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void agrgarDetalle(DetalleCompra detalle) {
        this.detalles.add(detalle);
    }

    public void aprobar() {
        this.estado = EstadoSolicitud.APROBADO;
    }

    public void rechazar() {
        this.estado = EstadoSolicitud.RECHAZADO;
    }

    public void actualizarEstado(EstadoSolicitud nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public void imprimir() {
        System.out.println( "SolicitudDeCompra{" +
                "id=" + id +
                ", fechaEmision=" + fechaEmision +
                ", numero='" + numero + '\'' +
                ", departamento=" + departamento +
                ", estado=" + estado +
                ", detalles=" + detalles +
                '}'
        );
    }
}