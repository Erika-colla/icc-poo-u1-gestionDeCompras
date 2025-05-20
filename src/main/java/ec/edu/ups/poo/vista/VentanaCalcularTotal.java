package ec.edu.ups.poo.vista;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import ec.edu.ups.poo.modelo.GestionDeComprasModelo;

public class VentanaCalcularTotal extends Frame {

    private GestionDeComprasModelo model;

    public VentanaCalcularTotal(String title, GestionDeComprasModelo model) {
        super(title);
        this.model = model;
        setSize(400, 200 );

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
            }
        });
    }
}