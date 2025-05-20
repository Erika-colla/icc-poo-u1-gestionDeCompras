package ec.edu.ups.poo.vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import ec.edu.ups.poo.clases.Producto;
import ec.edu.ups.poo.modelo.GestionDeComprasModelo;
import ec.edu.ups.poo.clases.ProductoAlimento;
import ec.edu.ups.poo.clases.ProductoRopa;
import ec.edu.ups.poo.clases.ProductoTecnologico;
import ec.edu.ups.poo.enums.UnidadDeMedida;
import java.util.GregorianCalendar;
import java.util.Calendar;


public class VentanaRegistrarProducto extends Frame implements ActionListener, ItemListener {

    private GestionDeComprasModelo model;

    private Panel panelCamposEspecificosAlimento;
    private Panel panelCamposEspecificosRopa;
    private Panel panelCamposEspecificosTecnologico;
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

    private Label etiquetaTipo;
    private CheckboxGroup grupoTipoProducto;
    private Checkbox cbTipoAlimento;
    private Checkbox cbTipoRopa;
    private Checkbox cbTipoTecnologico;

    private Label etiquetaFechaExpiracion;
    private TextField campoAnioExp;
    private TextField campoMesExp;
    private TextField campoDiaExp;

    private Label etiquetaTalla;
    private TextField campoTalla;

    private Button botonGuardar;
    private Button botonCerrar;

    private TextArea areaMensajes;

    private Panel panelContenedorCamposEspecificos;


    public VentanaRegistrarProducto(String title, GestionDeComprasModelo model) {
        super(title);
        this.model = model;

        setLayout(new BorderLayout());

        Panel panelTop = new Panel(new GridLayout(3, 1));

        Panel panelCamposComunes = new Panel(new GridLayout(3, 2));
        etiquetaId = new Label("ID:");
        campoId = new TextField(10);
        panelCamposComunes.add(etiquetaId);
        panelCamposComunes.add(campoId);

        etiquetaNombre = new Label("Nombre:");
        campoNombre = new TextField(20);
        panelCamposComunes.add(etiquetaNombre);
        panelCamposComunes.add(campoNombre);

        etiquetaPrecio = new Label("Precio Unitario:");
        campoPrecio = new TextField(10);
        panelCamposComunes.add(etiquetaPrecio);
        panelCamposComunes.add(campoPrecio);
        panelTop.add(panelCamposComunes);

        Panel panelUnidad = new Panel(new FlowLayout(FlowLayout.LEFT));
        etiquetaUnidad = new Label("Unidad de Medida:");
        panelUnidad.add(etiquetaUnidad);
        grupoUnidadMedida = new CheckboxGroup();
        cbTallaUnidad = new Checkbox(UnidadDeMedida.TALLA.name(), grupoUnidadMedida, false);
        cbKilogramo = new Checkbox(UnidadDeMedida.KILOGRAMO.name(), grupoUnidadMedida, false);
        cbLitro = new Checkbox(UnidadDeMedida.LITRO.name(), grupoUnidadMedida, false);
        cbMetro = new Checkbox(UnidadDeMedida.METRO.name(), grupoUnidadMedida, false);
        panelUnidad.add(cbTallaUnidad);
        panelUnidad.add(cbKilogramo);
        panelUnidad.add(cbLitro);
        panelUnidad.add(cbMetro);
        panelTop.add(panelUnidad);

        Panel panelTipo = new Panel(new FlowLayout(FlowLayout.LEFT));
        etiquetaTipo = new Label("Tipo de Producto:");
        panelTipo.add(etiquetaTipo);
        grupoTipoProducto = new CheckboxGroup();
        cbTipoAlimento = new Checkbox("Alimento", grupoTipoProducto, false);
        cbTipoRopa = new Checkbox("Ropa", grupoTipoProducto, false);
        cbTipoTecnologico = new Checkbox("Tecnológico", grupoTipoProducto, false);
        panelTipo.add(cbTipoAlimento);
        panelTipo.add(cbTipoRopa);
        panelTipo.add(cbTipoTecnologico);
        panelTop.add(panelTipo);


        add(panelTop, BorderLayout.NORTH);

        panelContenedorCamposEspecificos = new Panel(new BorderLayout());

        panelCamposEspecificosAlimento = new Panel(new GridLayout(1, 2));
        etiquetaFechaExpiracion = new Label("Fecha Expiración (Año Mes Día):");
        panelCamposEspecificosAlimento.add(etiquetaFechaExpiracion);
        Panel panelFecha = new Panel(new FlowLayout(FlowLayout.LEFT));
        campoAnioExp = new TextField(4);
        campoMesExp = new TextField(2);
        campoDiaExp = new TextField(2);
        panelFecha.add(campoAnioExp);
        panelFecha.add(new Label("/"));
        panelFecha.add(campoMesExp);
        panelFecha.add(new Label("/"));
        panelFecha.add(campoDiaExp);
        panelCamposEspecificosAlimento.add(panelFecha);
        panelCamposEspecificosAlimento.setVisible(false); // Inicialmente oculto

        panelCamposEspecificosRopa = new Panel(new GridLayout(1, 2));
        etiquetaTalla = new Label("Talla:");
        campoTalla = new TextField(10);
        panelCamposEspecificosRopa.add(etiquetaTalla);
        panelCamposEspecificosRopa.add(campoTalla);
        panelCamposEspecificosRopa.setVisible(false);

        panelCamposEspecificosTecnologico = new Panel(new FlowLayout());
        panelCamposEspecificosTecnologico.setVisible(false);

        panelContenedorCamposEspecificos.add(panelCamposEspecificosAlimento, BorderLayout.NORTH);
        panelContenedorCamposEspecificos.add(panelCamposEspecificosRopa, BorderLayout.CENTER);
        panelContenedorCamposEspecificos.add(panelCamposEspecificosTecnologico, BorderLayout.SOUTH);


        add(panelContenedorCamposEspecificos, BorderLayout.CENTER);


        areaMensajes = new TextArea("Info", 3, 40, TextArea.SCROLLBARS_VERTICAL_ONLY);
        areaMensajes.setEditable(false);
        add(areaMensajes, BorderLayout.SOUTH);


        Panel panelBotones = new Panel(new FlowLayout(FlowLayout.CENTER));
        botonGuardar = new Button("Guardar Producto");
        botonGuardar.addActionListener(this);
        botonCerrar = new Button("Cerrar Ventana");
        botonCerrar.addActionListener(this);
        panelBotones.add(botonGuardar);
        panelBotones.add(botonCerrar);

        add(panelBotones, BorderLayout.SOUTH);


        setSize(500, 550 );
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
            }
        });

        cbTipoAlimento.addItemListener(this);
        cbTipoRopa.addItemListener(this);
        cbTipoTecnologico.addItemListener(this);
        mostrarCamposEspecificos(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("Guardar Producto")) {
            areaMensajes.setText("");

            String idStr = campoId.getText().trim();
            String nombre = campoNombre.getText().trim();
            String precioStr = campoPrecio.getText().trim();
            Checkbox selectedUnitCheckbox = grupoUnidadMedida.getSelectedCheckbox();
            Checkbox selectedTypeCheckbox = grupoTipoProducto.getSelectedCheckbox();


            if (idStr.isEmpty() || nombre.isEmpty() || precioStr.isEmpty() || selectedUnitCheckbox == null || selectedTypeCheckbox == null) {
                areaMensajes.append("Los campos comunes, unidad de medida y tipo de producto son obligatorios.");
                return;
            }

            try {
                int id = Integer.parseInt(idStr);
                double precio = Double.parseDouble(precioStr);
                UnidadDeMedida medida = UnidadDeMedida.valueOf(selectedUnitCheckbox.getLabel().toUpperCase());

                String tipoProducto = selectedTypeCheckbox.getLabel();
                Producto nuevoProducto = null;

                if (tipoProducto.equals("Alimento")) {
                    String anioStr = campoAnioExp.getText().trim();
                    String mesStr = campoMesExp.getText().trim();
                    String diaStr = campoDiaExp.getText().trim();

                    if (anioStr.isEmpty() || mesStr.isEmpty() || diaStr.isEmpty()) {
                        areaMensajes.append("Campos de fecha de expiración obligatorios para Alimento.");
                        return;
                    }

                    int anio = Integer.parseInt(anioStr);
                    int mes = Integer.parseInt(mesStr) - 1;
                    int dia = Integer.parseInt(diaStr);

                    try {
                        GregorianCalendar fechaExpiracion = new GregorianCalendar(anio, mes, dia);
                        fechaExpiracion.setLenient(false);
                        fechaExpiracion.getTime();

                        nuevoProducto = new ProductoAlimento(id, nombre, precio, medida, fechaExpiracion);

                    } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException dateEx) {
                        areaMensajes.append("Fecha de expiración inválida (verifique Año, Mes, Día).");
                        return;
                    }


                } else if (tipoProducto.equals("Ropa")) {
                    String talla = campoTalla.getText().trim();

                    if (talla.isEmpty()) {
                        areaMensajes.append("Campo de talla obligatorio para Ropa.");
                        return;
                    }

                    nuevoProducto = new ProductoRopa(id, nombre, precio, medida, talla);

                } else if (tipoProducto.equals("Tecnológico")) {
                    nuevoProducto = new ProductoTecnologico(id, nombre, precio, medida);
                }

                if (nuevoProducto != null) {
                    model.addProducto(nuevoProducto);
                    areaMensajes.append("Producto registrado exitosamente: " + nuevoProducto.imprimirDetalle());
                    campoId.setText("");
                    campoNombre.setText("");
                    campoPrecio.setText("");
                    campoAnioExp.setText("");
                    campoMesExp.setText("");
                    campoDiaExp.setText("");
                    campoTalla.setText("");
                } else {
                    areaMensajes.append("Error al crear el producto (tipo no manejado).");
                }


            } catch (NumberFormatException ex) {
                areaMensajes.append("Error de formato numérico en campos ID, Precio, Año, Mes o Día.");
            } catch (IllegalArgumentException ex) {
                areaMensajes.append("Error en Unidad de Medida o Tipo de Producto seleccionado: " + ex.getMessage());
            } catch (Exception ex) {
                areaMensajes.append("Ocurrió un error inesperado al guardar el producto: " + ex.getMessage());
            }


        } else if (command.equals("Cerrar Ventana")) {
            setVisible(false);
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() instanceof Checkbox) {
            Checkbox selected = (Checkbox) e.getSource();
            if (selected.getCheckboxGroup() == grupoTipoProducto) {
                if (selected.getState()) {
                    mostrarCamposEspecificos(selected.getLabel());
                }
            }
        }
    }

    private void mostrarCamposEspecificos(String tipo) {
        panelCamposEspecificosAlimento.setVisible(false);
        panelCamposEspecificosRopa.setVisible(false);
        panelCamposEspecificosTecnologico.setVisible(false);

        if ("Alimento".equals(tipo)) {
            panelCamposEspecificosAlimento.setVisible(true);
        } else if ("Ropa".equals(tipo)) {
            panelCamposEspecificosRopa.setVisible(true);
        } else if ("Tecnológico".equals(tipo)) {
            panelCamposEspecificosTecnologico.setVisible(true);
        }
    }
}