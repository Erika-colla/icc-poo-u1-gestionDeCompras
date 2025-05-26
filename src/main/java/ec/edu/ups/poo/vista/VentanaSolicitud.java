package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.GestionDeComprasModelo;

import java.awt.*;
import java.awt.event.*;

public class VentanaSolicitud extends Frame implements ActionListener {

    private GestionDeComprasModelo modelo;
    private TextField campoNumero;
    private TextArea areaResultado;
    private Button botonAprobar, botonRechazar, botonCerrar;

    public VentanaSolicitud(String title, GestionDeComprasModelo modelo) {
        super(title);
        this.modelo = modelo;

        setLayout(new BorderLayout());

        // Panel de entrada
        Panel panelEntrada = new Panel(new FlowLayout());
        panelEntrada.add(new Label("Número de Solicitud:"));
        campoNumero = new TextField(20);
        panelEntrada.add(campoNumero);
        add(panelEntrada, BorderLayout.NORTH);

        // Área de resultados
        areaResultado = new TextArea(5, 50);
        areaResultado.setEditable(false);
        add(areaResultado, BorderLayout.CENTER);

        // Panel de botones
        Panel panelBotones = new Panel(new FlowLayout());
        botonAprobar = new Button("Aprobar");
        botonRechazar = new Button("Rechazar");
        botonCerrar = new Button("Cerrar");

        botonAprobar.addActionListener(this);
        botonRechazar.addActionListener(this);
        botonCerrar.addActionListener(this);

        panelBotones.add(botonAprobar);
        panelBotones.add(botonRechazar);
        panelBotones.add(botonCerrar);
        add(panelBotones, BorderLayout.SOUTH);

        // Configuración de la ventana
        setSize(500, 300);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String numero = campoNumero.getText().trim();
        if (numero.isEmpty()) {
            areaResultado.setText("Por favor ingrese el número de solicitud.");
            return;
        }

        if (e.getSource() == botonAprobar) {
            boolean exito = modelo.manejarEstadoSolicitud(numero, 1);
            if (exito) {
                areaResultado.setText("Solicitud aprobada correctamente.");
            } else {
                areaResultado.setText("No se encontró la solicitud o ya fue procesada.");
            }

        } else if (e.getSource() == botonRechazar) {
            boolean exito = modelo.manejarEstadoSolicitud(numero, 2);
            if (exito) {
                areaResultado.setText("Solicitud rechazada correctamente.");
            } else {
                areaResultado.setText("No se encontró la solicitud o ya fue procesada.");
            }

        } else if (e.getSource() == botonCerrar) {
            setVisible(false);
        }
    }
}