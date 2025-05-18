package ec.edu.ups.poo.vista;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import ec.edu.ups.poo.modelo.GestionDeComprasModelo;

public class VentanaRegistrarProveedor extends Frame {

    private GestionDeComprasModelo modelo;

    public VentanaRegistrarProveedor(String title, GestionDeComprasModelo modelo) {
        super(title);
        this.modelo = modelo;
        setSize(400, 300);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
            }
        });
    }
}