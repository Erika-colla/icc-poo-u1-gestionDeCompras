package ec.edu.ups.poo.vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import ec.edu.ups.poo.modelo.GestionDeComprasModelo;
import ec.edu.ups.poo.clases.Producto;
import java.util.List;

public class VentanaListarProductos extends Frame implements ActionListener {

    private GestionDeComprasModelo model;
    private TextArea areaListadoProductos;
    private Button botonActualizar;
    private Button botonRegresar;

    public VentanaListarProductos(String title, GestionDeComprasModelo model) {
        super(title);
        this.model = model;

        setLayout(new BorderLayout());

        areaListadoProductos = new TextArea("Listado de Productos...", 15, 60, TextArea.SCROLLBARS_VERTICAL_ONLY);
        areaListadoProductos.setEditable(false);
        add(areaListadoProductos, BorderLayout.CENTER);

        Panel panelBotones = new Panel(new FlowLayout(FlowLayout.CENTER));
        botonRegresar = new Button("Regresar");
        botonRegresar.addActionListener(this);
        panelBotones.add(botonRegresar);
        add(panelBotones, BorderLayout.SOUTH);

        setSize(600, 400);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
                dispose();
            }
        });

        cargarListaProductos();
    }

    private void cargarListaProductos() {
        areaListadoProductos.setText("");

        List<Producto> productos = model.getProductos();

        if (productos.isEmpty()) {
            areaListadoProductos.append("No hay productos registrados.");
        } else {
            areaListadoProductos.append("--- LISTADO DE PRODUCTOS ---\n");
            for (Producto p : productos) {
                areaListadoProductos.append("ID: " + p.getId() + "\n");
                areaListadoProductos.append("  Nombre: " + p.getNombre() + "\n");
                areaListadoProductos.append("  Precio Unitario: " + p.getPrecioUnitario() + "\n");
                areaListadoProductos.append("  Unidad de Medida: " + p.getMedida() + "\n");
                areaListadoProductos.append("--------------------------------\n");
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("Actualizar Lista")) {
            cargarListaProductos();
        } else if (command.equals("Regresar")) {
            setVisible(false);
            dispose();
        }
    }
}