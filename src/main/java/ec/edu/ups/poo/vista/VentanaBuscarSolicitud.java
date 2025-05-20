package ec.edu.ups.poo.vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import ec.edu.ups.poo.modelo.GestionDeComprasModelo;
import ec.edu.ups.poo.clases.SolicitudDeCompra;


public class VentanaBuscarSolicitud extends Frame implements ActionListener {

    private GestionDeComprasModelo modelo;

    private Label etiquetaNumero;
    private TextField campoNumero;
    private Button botonBuscar;
    private TextArea areaResultado;
    private Button botonCerrar;


    public VentanaBuscarSolicitud(String title, GestionDeComprasModelo modelo) {
        super(title);
        this.modelo = modelo;

        setLayout(new BorderLayout());


        Panel panelInput = new Panel(new FlowLayout(FlowLayout.LEFT));

        etiquetaNumero = new Label("Número de solicitud:");
        campoNumero = new TextField(15);
        botonBuscar = new Button("Buscar");
        botonBuscar.addActionListener(this);

        panelInput.add(etiquetaNumero);
        panelInput.add(campoNumero);
        panelInput.add(botonBuscar);

        add(panelInput, BorderLayout.NORTH);


        areaResultado = new TextArea("Resultado de la búsqueda...", 5, 60, TextArea.SCROLLBARS_VERTICAL_ONLY);
        areaResultado.setEditable(false);
        add(areaResultado, BorderLayout.CENTER);


        Panel panelBotonCerrar = new Panel(new FlowLayout(FlowLayout.CENTER));
        botonCerrar = new Button("Cerrar Ventana");
        botonCerrar.addActionListener(this);
        panelBotonCerrar.add(botonCerrar);
        add(panelBotonCerrar, BorderLayout.SOUTH);


        setSize(500, 350);
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

        if (command.equals("Buscar")) {
            areaResultado.setText("");
            String numeroBuscar = campoNumero.getText().trim();

            if (numeroBuscar.isEmpty()) {
                areaResultado.append("Por favor, ingrese un número de solicitud.");
                return;
            }

            SolicitudDeCompra solicitudEncontrada = modelo.findSolicitudByNumero(numeroBuscar);

            if (solicitudEncontrada != null) {
                areaResultado.append("Solicitud encontrada:\n");
                areaResultado.append(solicitudEncontrada.toString() + "\n");
            } else {
                areaResultado.append("No se encontró ninguna solicitud con el número: " + numeroBuscar);
            }

        } else if (command.equals("Cerrar Ventana")) {
            setVisible(false);
        }
    }
}