package ec.edu.ups.poo.vista;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import ec.edu.ups.poo.modelo.GestionDeComprasModelo;

public class VentanaBuscarProveedor extends Frame {

    private GestionDeComprasModelo modelo;

    public VentanaBuscarProveedor(String title, GestionDeComprasModelo modelo) {
        super(title);
        this.modelo = modelo;
        setSize(400, 200);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
            }
        });
    }
}