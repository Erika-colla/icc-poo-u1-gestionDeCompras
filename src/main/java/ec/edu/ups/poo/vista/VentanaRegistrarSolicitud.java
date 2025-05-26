package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.clases.Departamento;
import ec.edu.ups.poo.clases.DetalleCompra;
import ec.edu.ups.poo.clases.Empleado;
import ec.edu.ups.poo.clases.Producto;
import ec.edu.ups.poo.clases.SolicitudDeCompra;
import ec.edu.ups.poo.enums.EstadoSolicitud;
import ec.edu.ups.poo.modelo.GestionDeComprasModelo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class VentanaRegistrarSolicitud extends Frame implements ActionListener, ItemListener {

    private GestionDeComprasModelo model;

    private Label etiquetaIdSolicitud;
    private TextField campoIdSolicitud;
    private Label etiquetaNumeroSolicitud;
    private TextField campoNumeroSolicitud;
    private Label etiquetaFechaEmision;
    private TextField campoAnioEmision;
    private TextField campoMesEmision;
    private TextField campoDiaEmision;

    private Label etiquetaProducto;
    private Choice choiceProductos;
    private Label etiquetaCantidad;
    private TextField campoCantidad;
    private Button botonAgregarProducto;
    private List<DetalleCompra> detallesTemporales;
    private TextArea areaDetallesAgregados;

    private Checkbox checkboxDepartamento;
    private Label etiquetaIdEmpleado;
    private TextField campoIdEmpleado;
    private Label etiquetaNombreEmpleado;
    private TextField campoNombreEmpleado;
    private Label etiquetaCargoEmpleado;
    private TextField campoCargoEmpleado;
    private Label etiquetaTelefonoEmpleado;
    private TextField campoTelefonoEmpleado;
    private Panel panelCamposDepartamento;

    private Button botonGuardarSolicitud;
    private Button botonCerrar;
    private TextArea areaMensajes;

    public VentanaRegistrarSolicitud(String title, GestionDeComprasModelo model) {
        super(title);
        this.model = model;
        this.detallesTemporales = new ArrayList<>();

        setLayout(new BorderLayout(15, 15));
        setBackground(new Color(255, 255, 204));

        choiceProductos = new Choice();
        botonAgregarProducto = new Button("Agregar Producto");
        botonAgregarProducto.addActionListener(this);

        Panel panelSolicitudInfo = new Panel(new GridLayout(3, 2, 5, 5));
        panelSolicitudInfo.setBackground(new Color(255, 255, 204));

        etiquetaIdSolicitud = new Label("ID Solicitud:");
        campoIdSolicitud = new TextField(10);
        panelSolicitudInfo.add(etiquetaIdSolicitud);
        panelSolicitudInfo.add(campoIdSolicitud);

        etiquetaNumeroSolicitud = new Label("Número Solicitud:");
        campoNumeroSolicitud = new TextField(15);
        panelSolicitudInfo.add(etiquetaNumeroSolicitud);
        panelSolicitudInfo.add(campoNumeroSolicitud);

        etiquetaFechaEmision = new Label("Fecha Emisión (Año Mes Día):");
        panelSolicitudInfo.add(etiquetaFechaEmision);
        Panel panelFecha = new Panel(new FlowLayout(FlowLayout.LEFT));
        campoAnioEmision = new TextField(4);
        campoMesEmision = new TextField(2);
        campoDiaEmision = new TextField(2);
        panelFecha.add(campoAnioEmision);
        panelFecha.add(new Label("/"));
        panelFecha.add(campoMesEmision);
        panelFecha.add(new Label("/"));
        panelFecha.add(campoDiaEmision);
        panelSolicitudInfo.add(panelFecha);

        add(panelSolicitudInfo, BorderLayout.NORTH);

        Panel panelCentral = new Panel(new GridLayout(2, 1, 10, 10));
        panelCentral.setBackground(new Color(255, 255, 204));

        Panel panelDetalles = new Panel(new BorderLayout(5, 5));
        panelDetalles.setBackground(new Color(255, 255, 204));
        panelDetalles.add(new Label("--- Detalles de Productos ---", Label.CENTER), BorderLayout.NORTH);

        Panel panelAddProducto = new Panel(new FlowLayout(FlowLayout.LEFT));
        panelAddProducto.setBackground(new Color(255, 255, 204));

        etiquetaProducto = new Label("Producto:");
        panelAddProducto.add(etiquetaProducto);
        cargarProductosEnChoice();
        panelAddProducto.add(choiceProductos);

        etiquetaCantidad = new Label("Cantidad:");
        campoCantidad = new TextField(5);
        panelAddProducto.add(etiquetaCantidad);
        panelAddProducto.add(campoCantidad);

        panelAddProducto.add(botonAgregarProducto);
        panelDetalles.add(panelAddProducto, BorderLayout.NORTH);

        areaDetallesAgregados = new TextArea("Productos Agregados:", 5, 50, TextArea.SCROLLBARS_VERTICAL_ONLY);
        areaDetallesAgregados.setEditable(false);
        panelDetalles.add(areaDetallesAgregados, BorderLayout.CENTER);
        panelCentral.add(panelDetalles);

        Panel panelDepartamentoContenedor = new Panel(new BorderLayout(5, 5));
        panelDepartamentoContenedor.setBackground(new Color(255, 255, 204));
        panelDepartamentoContenedor.add(new Label("--- Departamento ---", Label.CENTER), BorderLayout.NORTH);

        checkboxDepartamento = new Checkbox("Asignar Departamento a la Solicitud");
        checkboxDepartamento.addItemListener(this);
        checkboxDepartamento.setState(true);
        panelDepartamentoContenedor.add(checkboxDepartamento, BorderLayout.NORTH);

        panelCamposDepartamento = new Panel(new GridLayout(4, 2, 5, 5));
        panelCamposDepartamento.setBackground(new Color(255, 255, 204));
        etiquetaIdEmpleado = new Label("ID Empleado (Responsable):");
        campoIdEmpleado = new TextField(10);
        etiquetaNombreEmpleado = new Label("Nombre Empleado:");
        campoNombreEmpleado = new TextField(20);
        etiquetaCargoEmpleado = new Label("Cargo Empleado:");
        campoCargoEmpleado = new TextField(20);
        etiquetaTelefonoEmpleado = new Label("Teléfono Empleado:");
        campoTelefonoEmpleado = new TextField(15);

        panelCamposDepartamento.add(etiquetaIdEmpleado);
        panelCamposDepartamento.add(campoIdEmpleado);
        panelCamposDepartamento.add(etiquetaNombreEmpleado);
        panelCamposDepartamento.add(campoNombreEmpleado);
        panelCamposDepartamento.add(etiquetaCargoEmpleado);
        panelCamposDepartamento.add(campoCargoEmpleado);
        panelCamposDepartamento.add(etiquetaTelefonoEmpleado);
        panelCamposDepartamento.add(campoTelefonoEmpleado);
        panelDepartamentoContenedor.add(panelCamposDepartamento, BorderLayout.CENTER);

        panelCentral.add(panelDepartamentoContenedor);
        add(panelCentral, BorderLayout.CENTER);

        areaMensajes = new TextArea("Info", 3, 40, TextArea.SCROLLBARS_VERTICAL_ONLY);
        areaMensajes.setEditable(false);

        Panel panelAbajo = new Panel(new BorderLayout());
        panelAbajo.add(areaMensajes, BorderLayout.NORTH);
        Panel panelBotonesFinales = new Panel(new FlowLayout(FlowLayout.CENTER));
        botonGuardarSolicitud = new Button("Guardar Solicitud");
        botonGuardarSolicitud.addActionListener(this);
        botonCerrar = new Button("Cerrar Ventana");
        botonCerrar.addActionListener(this);
        panelBotonesFinales.add(botonGuardarSolicitud);
        panelBotonesFinales.add(botonCerrar);
        panelAbajo.add(panelBotonesFinales, BorderLayout.SOUTH);
        add(panelAbajo, BorderLayout.SOUTH);

        setSize(700, 700);
        setResizable(false);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
                dispose();
            }
        });
    }

    private void cargarProductosEnChoice() {
        choiceProductos.removeAll();
        if (model.getProductos().isEmpty()) {
            choiceProductos.add("No hay productos disponibles");
            botonAgregarProducto.setEnabled(false);
        } else {
            botonAgregarProducto.setEnabled(true);
            for (Producto p : model.getProductos()) {
                choiceProductos.add(p.getId() + " - " + p.getNombre() + " ($" + p.getPrecioUnitario() + ")");
            }
        }
    }
    private void setCamposDepartamentoEditable(boolean editable) {
        campoIdEmpleado.setEditable(editable);
        campoNombreEmpleado.setEditable(editable);
        campoCargoEmpleado.setEditable(editable);
        campoTelefonoEmpleado.setEditable(editable);

        if (!editable) {
            campoIdEmpleado.setText("");
            campoNombreEmpleado.setText("");
            campoCargoEmpleado.setText("");
            campoTelefonoEmpleado.setText("");
        }
    }

    private void actualizarAreaDetalles() {
        areaDetallesAgregados.setText("Productos Agregados:\n");
        if (detallesTemporales.isEmpty()) {
            areaDetallesAgregados.append("  Ninguno\n");
            return;
        }
        for (int i = 0; i < detallesTemporales.size(); i++) {
            DetalleCompra dc = detallesTemporales.get(i);
            areaDetallesAgregados.append("  " + (i + 1) + ". " + dc.getProducto().getNombre() + " x " + dc.getCantidad() + " = $" + String.format("%.2f", dc.calcularTotal()) + "\n");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("Agregar Producto")) {
            areaMensajes.setText("");
            if (choiceProductos.getSelectedItem() == null || choiceProductos.getSelectedItem().startsWith("No hay")) {
                areaMensajes.append("No hay productos para agregar.\n");
                return;
            }

            String selectedProductText = choiceProductos.getSelectedItem();
            int productId = -1;
            try {
                productId = Integer.parseInt(selectedProductText.split(" ")[0]);
            } catch (NumberFormatException ex) {
                areaMensajes.append("Error al parsear ID de producto. Seleccione un producto válido.\n");
                return;
            }

            Producto selectedProduct = model.findProductoById(productId);

            String cantidadStr = campoCantidad.getText().trim();

            if (selectedProduct == null) {
                areaMensajes.append("Error: Producto seleccionado no encontrado.\n");
                return;
            }
            if (cantidadStr.isEmpty()) {
                areaMensajes.append("Ingrese la cantidad del producto.\n");
                return;
            }

            int cantidad = -1;
            try {
                cantidad = Integer.parseInt(cantidadStr);
            } catch (NumberFormatException ex) {
                areaMensajes.append("La cantidad debe ser un número entero válido.\n");
                return;
            }

            if (cantidad <= 0) {
                areaMensajes.append("La cantidad debe ser mayor que cero.\n");
                return;
            }

            detallesTemporales.add(new DetalleCompra(selectedProduct, cantidad));
            actualizarAreaDetalles();
            campoCantidad.setText("");
            areaMensajes.append("Producto agregado a la solicitud temporalmente.\n");

        } else if (command.equals("Guardar Solicitud")) {
            areaMensajes.setText("");

            String idSolicitudStr = campoIdSolicitud.getText().trim();
            String numeroSolicitud = campoNumeroSolicitud.getText().trim();
            String anioEmisionStr = campoAnioEmision.getText().trim();
            String mesEmisionStr = campoMesEmision.getText().trim();
            String diaEmisionStr = campoDiaEmision.getText().trim();

            if (idSolicitudStr.isEmpty() || numeroSolicitud.isEmpty() ||
                    anioEmisionStr.isEmpty() || mesEmisionStr.isEmpty() || diaEmisionStr.isEmpty()) {
                areaMensajes.append("Los campos básicos de la solicitud (ID, Número, Fecha) son obligatorios.\n");
                return;
            }

            if (detallesTemporales.isEmpty()) {
                areaMensajes.append("Debe agregar al menos un producto a la solicitud.\n");
                return;
            }

            int idSolicitud;
            try {
                idSolicitud = Integer.parseInt(idSolicitudStr);
            } catch (NumberFormatException ex) {
                areaMensajes.append("El ID de la solicitud debe ser un número entero válido.\n");
                return;
            }

            int anioEmision, mesEmision, diaEmision;
            try {
                anioEmision = Integer.parseInt(anioEmisionStr);
                mesEmision = Integer.parseInt(mesEmisionStr) - 1;
                diaEmision = Integer.parseInt(diaEmisionStr);
            } catch (NumberFormatException ex) {
                areaMensajes.append("La fecha de emisión debe contener números válidos (Año, Mes, Día).\n");
                return;
            }

            GregorianCalendar fechaEmision;
            try {
                fechaEmision = new GregorianCalendar(anioEmision, mesEmision, diaEmision);
                fechaEmision.setLenient(false);
                fechaEmision.getTime();
            } catch (IllegalArgumentException ex) {
                areaMensajes.append("Fecha de emisión inválida. Por favor, ingrese una fecha real.\n");
                return;
            }

            Departamento departamentoSeleccionado = null;

            if (checkboxDepartamento.getState()) {
                String idEmpStr = campoIdEmpleado.getText().trim();
                String nombreEmp = campoNombreEmpleado.getText().trim();
                String cargoEmp = campoCargoEmpleado.getText().trim();
                String telEmp = campoTelefonoEmpleado.getText().trim();

                if (idEmpStr.isEmpty() || nombreEmp.isEmpty() || cargoEmp.isEmpty() || telEmp.isEmpty()) {
                    areaMensajes.append("Todos los campos del departamento son obligatorios si desea asignarlo.\n");
                    return;
                }
                int idEmpleado;
                try {
                    idEmpleado = Integer.parseInt(idEmpStr);
                } catch (NumberFormatException ex) {
                    areaMensajes.append("El ID del empleado debe ser un número entero válido.\n");
                    return;
                }

                Empleado nuevoResponsable = new Empleado(idEmpleado, nombreEmp, cargoEmp, telEmp);
                departamentoSeleccionado = new Departamento(nuevoResponsable);
                model.addDepartamento(departamentoSeleccionado);
            }

            SolicitudDeCompra nuevaSolicitud = new SolicitudDeCompra(
                    idSolicitud,
                    new ArrayList<>(detallesTemporales),
                    EstadoSolicitud.SOLICITADA,
                    departamentoSeleccionado,
                    numeroSolicitud,
                    fechaEmision
            );
            model.addSolicitud(nuevaSolicitud);

            areaMensajes.append("Solicitud de Compra registrada exitosamente:\n" +
                    "ID: " + nuevaSolicitud.getId() + "\n" +
                    "Número: " + nuevaSolicitud.getNumero() + "\n" +
                    "Departamento: " + (nuevaSolicitud.getDepartamento() != null ? nuevaSolicitud.getDepartamento().getResponsable().getNombre() : "Ninguno") + "\n" +
                    "Estado: " + nuevaSolicitud.getEstado());

            // Limpiar campos y resetear estado
            campoIdSolicitud.setText("");
            campoNumeroSolicitud.setText("");
            campoAnioEmision.setText("");
            campoMesEmision.setText("");
            campoDiaEmision.setText("");
            campoCantidad.setText("");
            detallesTemporales.clear();
            actualizarAreaDetalles();
            checkboxDepartamento.setState(true);
            setCamposDepartamentoEditable(true);

        } else if (command.equals("Cerrar Ventana")) {
            setVisible(false);
            dispose();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == checkboxDepartamento) {
            setCamposDepartamentoEditable(checkboxDepartamento.getState());
        }
    }
}