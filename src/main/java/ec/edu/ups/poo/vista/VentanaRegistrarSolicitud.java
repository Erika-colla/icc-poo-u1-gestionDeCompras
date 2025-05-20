package ec.edu.ups.poo.vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import ec.edu.ups.poo.modelo.GestionDeComprasModelo;
import ec.edu.ups.poo.clases.Proveedor;
import ec.edu.ups.poo.clases.Producto;
import ec.edu.ups.poo.clases.SolicitudDeCompra;
import ec.edu.ups.poo.clases.DetalleCompra;
import ec.edu.ups.poo.clases.Departamento;
import ec.edu.ups.poo.enums.EstadoSolicitud;
import java.util.GregorianCalendar;
import java.util.ArrayList;
import java.util.List;

public class VentanaRegistrarSolicitud extends Frame implements ActionListener {

    private GestionDeComprasModelo model;

    private Label etiquetaIdSolicitud;
    private TextField campoIdSolicitud;

    private Label etiquetaFechaSolicitud;
    private TextField campoAnioSolicitud;
    private TextField campoMesSolicitud;
    private TextField campoDiaSolicitud;

    private Label etiquetaRucProveedor;
    private TextField campoRucProveedor;

    private Label etiquetaIdProducto;
    private TextField campoIdProducto;

    private Label etiquetaCantidadProducto;
    private TextField campoCantidadProducto;


    private Button botonGuardar;
    private Button botonRegresar;

    private TextArea areaMensajes;


    public VentanaRegistrarSolicitud(String title, GestionDeComprasModelo model) {
        super(title);
        this.model = model;

        setLayout(new BorderLayout());

        Panel panelFormulario = new Panel(new GridLayout(6, 2, 10, 5));

        etiquetaIdSolicitud = new Label("ID Solicitud:");
        campoIdSolicitud = new TextField(10);
        panelFormulario.add(etiquetaIdSolicitud);
        panelFormulario.add(campoIdSolicitud);

        etiquetaFechaSolicitud = new Label("Fecha (Año Mes Día):");
        Panel panelFecha = new Panel(new FlowLayout(FlowLayout.LEFT));
        campoAnioSolicitud = new TextField(4);
        campoMesSolicitud = new TextField(2);
        campoDiaSolicitud = new TextField(2);
        panelFecha.add(campoAnioSolicitud);
        panelFecha.add(new Label("/"));
        panelFecha.add(campoMesSolicitud);
        panelFecha.add(new Label("/"));
        panelFecha.add(campoDiaSolicitud);
        panelFormulario.add(etiquetaFechaSolicitud);
        panelFormulario.add(panelFecha);

        etiquetaRucProveedor = new Label("RUC Proveedor:");
        campoRucProveedor = new TextField(15);
        panelFormulario.add(etiquetaRucProveedor);
        panelFormulario.add(campoRucProveedor);

        etiquetaIdProducto = new Label("ID Producto:");
        campoIdProducto = new TextField(10);
        panelFormulario.add(etiquetaIdProducto);
        panelFormulario.add(campoIdProducto);

        etiquetaCantidadProducto = new Label("Cantidad Producto:");
        campoCantidadProducto = new TextField(10);
        panelFormulario.add(etiquetaCantidadProducto);
        panelFormulario.add(campoCantidadProducto);

        add(panelFormulario, BorderLayout.NORTH);

        areaMensajes = new TextArea("Info", 5, 40, TextArea.SCROLLBARS_VERTICAL_ONLY);
        areaMensajes.setEditable(false);
        add(areaMensajes, BorderLayout.CENTER);

        Panel panelBotones = new Panel(new FlowLayout(FlowLayout.CENTER));
        botonGuardar = new Button("Guardar Solicitud");
        botonGuardar.addActionListener(this);
        botonRegresar = new Button("Regresar");
        botonRegresar.addActionListener(this);
        panelBotones.add(botonGuardar);
        panelBotones.add(botonRegresar);

        add(panelBotones, BorderLayout.SOUTH);


        setSize(500, 480);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
                dispose();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        areaMensajes.setText("");

        if (command.equals("Guardar Solicitud")) {
            String idSolicitudStr = campoIdSolicitud.getText().trim();
            String anioStr = campoAnioSolicitud.getText().trim();
            String mesStr = campoMesSolicitud.getText().trim();
            String diaStr = campoDiaSolicitud.getText().trim();
            String rucProveedorStr = campoRucProveedor.getText().trim();
            String idProductoStr = campoIdProducto.getText().trim();
            String cantidadProductoStr = campoCantidadProducto.getText().trim();

            if (idSolicitudStr.isEmpty() || anioStr.isEmpty() || mesStr.isEmpty() || diaStr.isEmpty() ||
                    rucProveedorStr.isEmpty() || idProductoStr.isEmpty() || cantidadProductoStr.isEmpty()) {
                areaMensajes.append("Todos los campos son obligatorios.");
                return;
            }

            int idSolicitud = Integer.parseInt(idSolicitudStr);
            int anio = Integer.parseInt(anioStr);
            int mes = Integer.parseInt(mesStr) - 1;
            int dia = Integer.parseInt(diaStr);
            int rucProveedor = Integer.parseInt(rucProveedorStr);
            int idProducto = Integer.parseInt(idProductoStr);
            int cantidadProducto = Integer.parseInt(cantidadProductoStr);

            GregorianCalendar fechaSolicitud = new GregorianCalendar(anio, mes, dia);
            fechaSolicitud.setLenient(false);
            fechaSolicitud.getTime();

            Proveedor proveedor = model.findProveedorByRuc(rucProveedor);
            if (proveedor == null) {
                areaMensajes.append("Error: Proveedor con RUC " + rucProveedorStr + " no encontrado.");
                return;
            }

            Producto producto = model.findProductoById(idProducto);
            if (producto == null) {
                areaMensajes.append("Error: Producto con ID " + idProducto + " no encontrado.");
                return;
            }

            if (cantidadProducto <= 0) {
                areaMensajes.append("La cantidad del producto debe ser mayor a cero.");
                return;
            }

            DetalleCompra detalle = new DetalleCompra(producto, cantidadProducto);

            List<DetalleCompra> detallesParaSolicitud = new ArrayList<>();
            detallesParaSolicitud.add(detalle);

            EstadoSolicitud estadoInicial = EstadoSolicitud.REVISION;
            Departamento departamentoAsignado = null;

            SolicitudDeCompra nuevaSolicitud = new SolicitudDeCompra(
                    idSolicitud,
                    detallesParaSolicitud,
                    estadoInicial,
                    departamentoAsignado,
                    String.valueOf(idSolicitud),
                    fechaSolicitud
            );

            model.addSolicitud(nuevaSolicitud);

            areaMensajes.append("Solicitud de compra registrada exitosamente con ID: " + idSolicitud);
            areaMensajes.append("\nDetalle agregado: " + producto.getNombre() + " (Cantidad: " + cantidadProducto + ")");

            campoIdSolicitud.setText("");
            campoAnioSolicitud.setText("");
            campoMesSolicitud.setText("");
            campoDiaSolicitud.setText("");
            campoRucProveedor.setText("");
            campoIdProducto.setText("");
            campoCantidadProducto.setText("");

        } else if (command.equals("Regresar")) {
            setVisible(false);
            dispose();
        }
    }
}