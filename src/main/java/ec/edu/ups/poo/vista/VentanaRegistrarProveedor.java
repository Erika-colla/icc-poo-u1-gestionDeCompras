package ec.edu.ups.poo.vista;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import ec.edu.ups.poo.modelo.GestionDeComprasModelo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import ec.edu.ups.poo.modelo.GestionDeComprasModelo;
import ec.edu.ups.poo.clases.Proveedor;


public class VentanaRegistrarProveedor extends Frame implements ActionListener {

    private ec.edu.ups.poo.modelo.GestionDeComprasModelo model;

    private Label etiquetaNombre;
    private TextField campoNombre;

    private Label etiquetaRuc;
    private TextField campoRuc;

    private Label etiquetaDireccion;
    private TextField campoDireccion;

    private Label etiquetaTelefono;
    private TextField campoTelefono;

    private Button botonGuardar;
    private Button botonCerrar;

    private TextArea areaMensajes;


    public VentanaRegistrarProveedor(String title, ec.edu.ups.poo.modelo.GestionDeComprasModelo model) {
        super(title);
        this.model = model;

        setLayout(new BorderLayout());


        Panel panelFormulario = new Panel(new GridLayout(4, 2, 10, 5));

        etiquetaNombre = new Label("Nombre:");
        campoNombre = new TextField(20);
        panelFormulario.add(etiquetaNombre);
        panelFormulario.add(campoNombre);
        panelFormulario.setBackground(new Color(255, 255, 204));

        etiquetaRuc = new Label("RUC:");
        campoRuc = new TextField(20);
        panelFormulario.add(etiquetaRuc);
        panelFormulario.add(campoRuc);

        etiquetaDireccion = new Label("Dirección:");
        campoDireccion = new TextField(20);
        panelFormulario.add(etiquetaDireccion);
        panelFormulario.add(campoDireccion);

        etiquetaTelefono = new Label("Teléfono:");
        campoTelefono = new TextField(20);
        panelFormulario.add(etiquetaTelefono);
        panelFormulario.add(campoTelefono);

        add(panelFormulario, BorderLayout.NORTH);

        areaMensajes = new TextArea("Ingresar Datos", 3, 40);
        areaMensajes.setEditable(false);
        add(areaMensajes, BorderLayout.CENTER);

        Panel panelBotones = new Panel(new FlowLayout(FlowLayout.CENTER));
        botonGuardar = new Button("Guardar");
        botonGuardar.addActionListener(this);
        botonCerrar = new Button("Cerrar Ventana");
        botonCerrar.addActionListener(this);
        panelBotones.add(botonGuardar);
        panelBotones.add(botonCerrar);

        add(panelBotones, BorderLayout.SOUTH);

        setSize(400, 350);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("Guardar")) {
            areaMensajes.setText("");

            String nombre = campoNombre.getText();
            String rucStr = campoRuc.getText();
            String direccion = campoDireccion.getText();
            String telefonoStr = campoTelefono.getText();

            int ruc = Integer.parseInt(rucStr);
            int telefono = Integer.parseInt(telefonoStr);

            Proveedor nuevoProveedor = new Proveedor(nombre, ruc, direccion, telefono);
            model.addProveedor(nuevoProveedor);

            areaMensajes.append("Proveedor registrado: " + nuevoProveedor.toString());

            campoNombre.setText("");
            campoRuc.setText("");
            campoDireccion.setText("");
            campoTelefono.setText("");

        } else if (command.equals("Cerrar Ventana")) {
            setVisible(false);
        }
    }
}