package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.GestionDeComprasModelo;
import ec.edu.ups.poo.clases.ProductoRopa;
import ec.edu.ups.poo.enums.UnidadDeMedida;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaRegistrarProductoRopa extends Frame implements ActionListener {

    private GestionDeComprasModelo model;

    private Label etiquetaId;
    private TextField campoId;
    private Label etiquetaNombre;
    private TextField campoNombre;
    private Label etiquetaPrecio;
    private TextField campoPrecio;
    private Label etiquetaUnidad;
    private CheckboxGroup grupoUnidadMedida;
    private Checkbox cbTallaUnidad;
    private Checkbox cbKilogramo;
    private Checkbox cbLitro;
    private Checkbox cbMetro;

    private Label etiquetaTalla;
    private TextField campoTalla;

    private Button botonGuardar;
    private Button botonCerrar;
    private TextArea areaMensajes;

    public VentanaRegistrarProductoRopa(String title, GestionDeComprasModelo model) {
        super(title);
        this.model = model;

        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(255, 255, 204));

        Panel panelGeneral = new Panel(new GridLayout(5, 2, 5, 5));

        etiquetaId = new Label("ID:");
        campoId = new TextField(10);
        panelGeneral.add(etiquetaId);
        panelGeneral.add(campoId);

        etiquetaNombre = new Label("Nombre:");
        campoNombre = new TextField(20);
        panelGeneral.add(etiquetaNombre);
        panelGeneral.add(campoNombre);

        etiquetaPrecio = new Label("Precio Unitario:");
        campoPrecio = new TextField(10);
        panelGeneral.add(etiquetaPrecio);
        panelGeneral.add(campoPrecio);

        etiquetaUnidad = new Label("Unidad de Medida:");
        panelGeneral.add(etiquetaUnidad);
        Panel panelUnidad = new Panel(new FlowLayout(FlowLayout.LEFT));
        grupoUnidadMedida = new CheckboxGroup();
        cbTallaUnidad = new Checkbox(UnidadDeMedida.TALLA.name(), grupoUnidadMedida, false);
        cbKilogramo = new Checkbox(UnidadDeMedida.KILOGRAMO.name(), grupoUnidadMedida, false);
        cbLitro = new Checkbox(UnidadDeMedida.LITRO.name(), grupoUnidadMedida, false);
        cbMetro = new Checkbox(UnidadDeMedida.METRO.name(), grupoUnidadMedida, false);
        panelUnidad.add(cbTallaUnidad);
        panelUnidad.add(cbKilogramo);
        panelUnidad.add(cbLitro);
        panelUnidad.add(cbMetro);
        panelGeneral.add(panelUnidad);

        etiquetaTalla = new Label("Talla:");
        campoTalla = new TextField(10);
        panelGeneral.add(etiquetaTalla);
        panelGeneral.add(campoTalla);

        add(panelGeneral, BorderLayout.NORTH);

        areaMensajes = new TextArea("Info", 3, 40, TextArea.SCROLLBARS_VERTICAL_ONLY);
        areaMensajes.setEditable(false);
        add(areaMensajes, BorderLayout.CENTER);

        Panel panelBotones = new Panel(new FlowLayout(FlowLayout.CENTER));
        botonGuardar = new Button("Guardar Producto Ropa");
        botonGuardar.addActionListener(this);
        botonCerrar = new Button("Cerrar Ventana");
        botonCerrar.addActionListener(this);
        panelBotones.add(botonGuardar);
        panelBotones.add(botonCerrar);
        add(panelBotones, BorderLayout.SOUTH);

        setSize(500, 400);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("Guardar Producto Ropa")) {
            areaMensajes.setText("");

            String idStr = campoId.getText().trim();
            String nombre = campoNombre.getText().trim();
            String precioStr = campoPrecio.getText().trim();
            Checkbox selectedUnitCheckbox = grupoUnidadMedida.getSelectedCheckbox();

            String talla = campoTalla.getText().trim();

            if (idStr.isEmpty() || nombre.isEmpty() || precioStr.isEmpty() || selectedUnitCheckbox == null ||
                    talla.isEmpty()) {
                areaMensajes.append("Todos los campos son obligatorios.");
                return;
            }

            int id = Integer.parseInt(idStr);
            double precio = Double.parseDouble(precioStr);
            UnidadDeMedida medida = UnidadDeMedida.valueOf(selectedUnitCheckbox.getLabel().toUpperCase());

            ProductoRopa nuevaRopa = new ProductoRopa(id, nombre, precio, medida, talla);
            model.addProducto(nuevaRopa);
            areaMensajes.append("Producto Ropa registrado exitosamente:\n" + nuevaRopa.imprimirDetalle());

            campoId.setText("");
            campoNombre.setText("");
            campoPrecio.setText("");
            grupoUnidadMedida.setSelectedCheckbox(null);
            campoTalla.setText("");

        } else if (command.equals("Cerrar Ventana")) {
            setVisible(false);
            dispose();
        }
    }
}