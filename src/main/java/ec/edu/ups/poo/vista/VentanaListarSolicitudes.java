package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.clases.SolicitudDeCompra;
import ec.edu.ups.poo.clases.DetalleCompra;
import ec.edu.ups.poo.modelo.GestionDeComprasModelo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.List;

public class VentanaListarSolicitudes extends Frame implements ActionListener {

    private GestionDeComprasModelo model;
    private TextArea areaListadoSolicitudes;
    private Button botonActualizar;
    private Button botonCerrar;

    public VentanaListarSolicitudes(String title, GestionDeComprasModelo model) {
        super(title);
        this.model = model;

        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(240, 248, 255));

        areaListadoSolicitudes = new TextArea("Cargando solicitudes...", 20, 80, TextArea.SCROLLBARS_VERTICAL_ONLY);
        areaListadoSolicitudes.setEditable(false);
        add(areaListadoSolicitudes, BorderLayout.CENTER);

        Panel panelBotones = new Panel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.setBackground(new Color(240, 248, 255));

        botonActualizar = new Button("Actualizar Lista");
        botonActualizar.addActionListener(this);
        panelBotones.add(botonActualizar);

        botonCerrar = new Button("Cerrar");
        botonCerrar.addActionListener(this);
        panelBotones.add(botonCerrar);

        add(panelBotones, BorderLayout.SOUTH);

        setSize(800, 600);
        setResizable(true);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
                dispose();
            }
        });

        cargarSolicitudes();
    }

    private void cargarSolicitudes() {
        areaListadoSolicitudes.setText("");
        List<SolicitudDeCompra> solicitudes = model.getSolicitudes();

        if (solicitudes == null || solicitudes.isEmpty()) {
            areaListadoSolicitudes.append("No hay solicitudes de compra registradas.\n");
            return;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for (SolicitudDeCompra sol : solicitudes) {
            areaListadoSolicitudes.append("----------------------------------------------------\n");
            areaListadoSolicitudes.append("ID Solicitud: " + sol.getId() + "\n");
            areaListadoSolicitudes.append("Número: " + sol.getNumero() + "\n");
            areaListadoSolicitudes.append("Fecha Emisión: " + sdf.format(sol.getFechaEmision().getTime()) + "\n");
            areaListadoSolicitudes.append("Estado: " + sol.getEstado().toString() + "\n");
            if (sol.getDepartamento() != null && sol.getDepartamento().getResponsable() != null) {
                areaListadoSolicitudes.append("Departamento: " + sol.getDepartamento().getResponsable().getNombre() + " (ID: " + sol.getDepartamento().getResponsable().getId() + ")\n");
            } else {
                areaListadoSolicitudes.append("Departamento: [No asignado/Responsable nulo]\n");
            }

            areaListadoSolicitudes.append("Detalles de Productos:\n");

            List detallesGenericos = sol.getList();

            if (detallesGenericos != null && !detallesGenericos.isEmpty()) {
                double totalSolicitud = 0;

                for (Object obj : detallesGenericos) {
                    if (obj instanceof DetalleCompra) {
                        DetalleCompra det = (DetalleCompra) obj;
                        if (det.getProducto() != null) {
                            areaListadoSolicitudes.append("  - " + det.getProducto().getNombre() + " (ID: " + det.getProducto().getId() + ") | Cantidad: " + det.getCantidad() + " | P.Unitario: $" + String.format("%.2f", det.getProducto().getPrecioUnitario()) + " | Subtotal: $" + String.format("%.2f", det.calcularTotal()) + "\n");
                            totalSolicitud += det.calcularTotal();
                        } else {
                            areaListadoSolicitudes.append("  - Producto no disponible para un detalle (Producto nulo).\n");
                        }
                    } else {
                        areaListadoSolicitudes.append("  - Elemento inesperado en la lista de detalles (no es DetalleCompra).\n");
                    }
                }
                areaListadoSolicitudes.append("  Total de la Solicitud: $" + String.format("%.2f", totalSolicitud) + "\n");
            } else {
                areaListadoSolicitudes.append("  No hay detalles de productos para esta solicitud.\n");
            }

            areaListadoSolicitudes.append("----------------------------------------------------\n\n");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Actualizar Lista")) {
            cargarSolicitudes();
        } else if (command.equals("Cerrar")) {
            setVisible(false);
            dispose();
        }
    }
}