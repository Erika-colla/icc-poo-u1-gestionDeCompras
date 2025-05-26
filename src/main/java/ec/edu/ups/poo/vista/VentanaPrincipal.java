package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.GestionDeComprasModelo;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaPrincipal extends Frame implements ActionListener {

    private GestionDeComprasModelo modelo;

    private Panel panelContenedorPrincipal;
    private Panel panelTitulo;
    private Panel panelMenuPrincipal;

    private Button botonRegistrarProveedor;
    private Button botonRegistrarProducto;
    private Button botonRegistrarSolicitud;
    private Button botonListarProveedores;
    private Button botonListarProductos;
    private Button botonListarSolicitudes;
    private Button botonBuscarProveedor;
    private Button botonBuscarProducto;
    private Button botonBuscarSolicitud;
    private Button botonManejarSolicitud;
    private Button botonCalcularTotal;
    private Button botonSalir;

    public VentanaPrincipal(String title, GestionDeComprasModelo modelo) {
        super(title);
        this.modelo = modelo;

        setBackground(new Color(255, 255, 204));
        setLayout(new BorderLayout(10, 10));

        panelContenedorPrincipal = new Panel();
        panelContenedorPrincipal.setLayout(new BorderLayout(10, 10));
        panelContenedorPrincipal.setBackground(new Color(255, 255, 204));

        panelTitulo = new Panel();
        panelTitulo.setLayout(new FlowLayout(FlowLayout.CENTER));

        Label tituloLabel = new Label("Sistema 'Gestión de Compras'");
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panelTitulo.add(tituloLabel);
        panelContenedorPrincipal.add(panelTitulo, BorderLayout.NORTH);

        panelMenuPrincipal = new Panel();
        panelMenuPrincipal.setLayout(new GridLayout(6, 2, 10, 10));
        panelMenuPrincipal.setBackground(new Color(255, 255, 204));

        botonRegistrarProveedor = new Button("1. Registrar proveedor");
        botonRegistrarProducto = new Button("2. Registrar producto");
        botonRegistrarSolicitud = new Button("3. Registrar solicitud de compra");
        botonListarProveedores = new Button("4. Listar proveedores");
        botonListarProductos = new Button("5. Listar productos");
        botonListarSolicitudes = new Button("6. Listar solicitudes de compra");
        botonBuscarProveedor = new Button("7. Buscar proveedor por ID");
        botonBuscarProducto = new Button("8. Buscar producto por nombre");
        botonBuscarSolicitud = new Button("9. Buscar solicitud por número");
        botonManejarSolicitud = new Button("10. Aprobar / Rechazar solicitud de compra");
        botonCalcularTotal = new Button("11. Calcular total de una solicitud");
        botonSalir = new Button("12. Salir");

        botonRegistrarProveedor.addActionListener(this);
        botonRegistrarProducto.addActionListener(this);
        botonRegistrarSolicitud.addActionListener(this);
        botonListarProveedores.addActionListener(this);
        botonListarProductos.addActionListener(this);
        botonListarSolicitudes.addActionListener(this);
        botonBuscarProveedor.addActionListener(this);
        botonBuscarProducto.addActionListener(this);
        botonBuscarSolicitud.addActionListener(this);
        botonManejarSolicitud.addActionListener(this);
        botonCalcularTotal.addActionListener(this);
        botonSalir.addActionListener(this);

        panelMenuPrincipal.add(botonRegistrarProveedor);
        panelMenuPrincipal.add(botonRegistrarProducto);
        panelMenuPrincipal.add(botonRegistrarSolicitud);
        panelMenuPrincipal.add(botonListarProveedores);
        panelMenuPrincipal.add(botonListarProductos);
        panelMenuPrincipal.add(botonListarSolicitudes);
        panelMenuPrincipal.add(botonBuscarProveedor);
        panelMenuPrincipal.add(botonBuscarProducto);
        panelMenuPrincipal.add(botonBuscarSolicitud);
        panelMenuPrincipal.add(botonManejarSolicitud);
        panelMenuPrincipal.add(botonCalcularTotal);
        panelMenuPrincipal.add(botonSalir);

        panelContenedorPrincipal.add(panelMenuPrincipal, BorderLayout.CENTER);
        add(panelContenedorPrincipal, BorderLayout.CENTER);

        setSize(550, 400);
        setResizable(false);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("1. Registrar proveedor")) {
            VentanaRegistrarProveedor ventana = new VentanaRegistrarProveedor("Registrar Proveedor", modelo);
            ventana.setVisible(true);
        } else if (command.equals("2. Registrar producto")) {
            VentanaRegistrarProducto ventana = new VentanaRegistrarProducto("Registrar Producto", modelo);
            ventana.setVisible(true);
        } else if (command.equals("3. Registrar solicitud de compra")) {
            VentanaRegistrarSolicitud ventana = new VentanaRegistrarSolicitud("Registrar Solicitud", modelo);
            ventana.setVisible(true);
        } else if (command.equals("4. Listar proveedores")) {
            VentanaListarProveedores ventanaLista = new VentanaListarProveedores("Listar Proveedores", modelo);
            ventanaLista.setVisible(true);
        } else if (command.equals("5. Listar productos")) {
            VentanaListarProductos ventana = new VentanaListarProductos("Listar Productos", modelo);
            ventana.setVisible(true);
        } else if (command.equals("6. Listar solicitudes de compra")) {
            VentanaListarSolicitudes ventana = new VentanaListarSolicitudes("Listar Solicitudes", modelo);
            ventana.setVisible(true);
        } else if (command.equals("7. Buscar proveedor por ID")) {
            VentanaBuscarProveedor ventana = new VentanaBuscarProveedor("Buscar Proveedor", modelo);
            ventana.setVisible(true);
        } else if (command.equals("8. Buscar producto por nombre")) {
            VentanaBuscarProducto ventana = new VentanaBuscarProducto("Buscar Producto", modelo);
            ventana.setVisible(true);
        } else if (command.equals("9. Buscar solicitud por número")) {
            VentanaBuscarSolicitud ventana = new VentanaBuscarSolicitud("Buscar Solicitud", modelo);
            ventana.setVisible(true);
        } else if (command.equals("10. Aprobar / Rechazar solicitud de compra")) {
            VentanaSolicitud ventana = new VentanaSolicitud("Aprobar / Rechazar Solicitud", modelo);
            ventana.setVisible(true);
        } else if (command.equals("11. Calcular total de una solicitud")) {
            VentanaCalcularTotal ventana = new VentanaCalcularTotal("Calcular Total Solicitud", modelo);
            ventana.setVisible(true);
        } else if (command.equals("12. Salir")) {
            System.exit(0);
        }
    }
}