package ec.edu.ups.poo.clases;

import ec.edu.ups.poo.enums.EstadoSolicitud;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Proveedor> proveedores = new ArrayList<>();
    private static List<Producto> productos = new ArrayList<>();
    private static List<SolicitudDeCompra> solicitudes = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean continuar = true;

        while (continuar) {
            mostrarMenu();
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    registrarProveedor();
                    break;
                case 2:
                    registrarProducto();
                    break;
                case 3:
                    registrarSolicitudDeCompra();
                    break;
                case 4:
                    listarProveedores();
                    break;
                case 5:
                    listarProductos();
                    break;
                case 6:
                    listarSolicitudesDeCompra();
                    break;
                case 7:
                    buscarProveedorPorID();
                    break;
                case 8:
                    buscarProductoPorNombre();
                    break;
                case 9:
                    buscarSolicitudPorNumero();
                    break;
                case 10:
                    aprobarRechazarSolicitud();
                    break;
                case 11:
                    calcularTotalSolicitud();
                    break;
                case 12:
                    System.out.println("Saliendo...");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("\nMenú:");
        System.out.println("1. Registrar proveedor");
        System.out.println("2. Registrar producto");
        System.out.println("3. Registrar solicitud de compra");
        System.out.println("4. Listar proveedores");
        System.out.println("5. Listar productos");
        System.out.println("6. Listar solicitudes de compra");
        System.out.println("7. Buscar proveedor por ID");
        System.out.println("8. Buscar producto por nombre");
        System.out.println("9. Buscar solicitud por número");
        System.out.println("10. Aprobar / Rechazar solicitud de compra");
        System.out.println("11. Calcular total de una solicitud");
        System.out.println("12. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void registrarProveedor() {
        System.out.println("Ingrese el nombre del proveedor: ");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese el ID del proveedor: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Proveedor proveedor = new Proveedor(id, nombre);
        proveedores.add(proveedor);
        System.out.println("Proveedor registrado: " + proveedor);
    }

    private static void registrarProducto() {
        System.out.println("Ingrese tipo de producto (Tecnologico, Ropa, Alimentos): ");
        String tipo = scanner.nextLine();
        System.out.println("Ingrese nombre del producto: ");
        String nombre = scanner.nextLine();

        double precio = -1;

        while (precio < 0) {
            System.out.println("Ingrese precio unitario: ");
            if (scanner.hasNextDouble()) {
                precio = scanner.nextDouble();
                if (precio < 0) {
                    System.out.println("El precio debe ser un número positivo.");
                }
            } else {
                System.out.println("Por favor ingrese un precio válido (número con decimales).");
                scanner.next();
            }
        }
        scanner.nextLine();

        Producto producto = null;
        switch (tipo.toLowerCase()) {
            case "tecnologico":
                producto = new ProductoTecnologico(productos.size() + 1, nombre, precio);
                break;
            case "ropa":
                producto = new ProductoRopa(productos.size() + 1, nombre, precio);
                break;
            case "alimentos":
                producto = new ProductoAlimento(productos.size() + 1, nombre, precio);
                break;
            default:
                System.out.println("Tipo de producto no válido.");
                return;
        }

        productos.add(producto);
        System.out.println("Producto registrado: " + producto.imprimirDetalle());
    }

    private static void registrarSolicitudDeCompra() {
        System.out.println("Ingrese el número de solicitud: ");
        String numero = scanner.nextLine();
        System.out.println("Ingrese el ID de la solicitud: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ingrese el estado de la solicitud (SOLICITADA, APROBADO, RECHAZADO): ");
        String estado = scanner.nextLine();
        EstadoSolicitud estadoSolicitud = EstadoSolicitud.valueOf(estado.toUpperCase());

        List<DetalleCompra> detalles = new ArrayList<>();
        System.out.println("¿Cuántos productos tiene la solicitud?");
        int cantidadProductos = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < cantidadProductos; i++) {
            System.out.println("Ingrese el ID del producto: ");
            int idProducto = scanner.nextInt();
            scanner.nextLine();
            Producto producto = null;
            for (Producto p : productos) {
                if (p.getId() == idProducto) {
                    producto = p;
                    break;
                }
            }

            if (producto != null) {
                System.out.println("Ingrese la cantidad del producto: ");
                int cantidad = scanner.nextInt();
                scanner.nextLine();
                detalles.add(new DetalleCompra(producto, cantidad));
            } else {
                System.out.println("Producto no encontrado.");
            }
        }

        SolicitudDeCompra solicitud = new SolicitudDeCompra(id, detalles, estadoSolicitud, numero, new GregorianCalendar());
        solicitudes.add(solicitud);
        System.out.println("Solicitud de compra registrada: ");
        solicitud.imprimir();
    }

    private static void listarProveedores() {
        if (proveedores.isEmpty()) {
            System.out.println("No hay proveedores registrados.");
        } else {
            System.out.println("Lista de proveedores:");
            for (Proveedor proveedor : proveedores) {
                System.out.println(proveedor);
            }
        }
    }

    private static void listarProductos() {
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
        } else {
            System.out.println("Lista de productos:");
            for (Producto producto : productos) {
                System.out.println(producto.imprimirDetalle());
            }
        }
    }

    private static void listarSolicitudesDeCompra() {
        if (solicitudes.isEmpty()) {
            System.out.println("No hay solicitudes de compra registradas.");
        } else {
            System.out.println("Lista de solicitudes de compra:");
            for (SolicitudDeCompra solicitud : solicitudes) {
                solicitud.imprimir();
            }
        }
    }

    private static void buscarProveedorPorID() {
        System.out.println("Ingrese el ID del proveedor a buscar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Proveedor proveedorEncontrado = null;
        for (Proveedor proveedor : proveedores) {
            if (proveedor.getId() == id) {
                proveedorEncontrado = proveedor;
                break;
            }
        }

        if (proveedorEncontrado != null) {
            System.out.println("Proveedor encontrado: " + proveedorEncontrado);
        } else {
            System.out.println("Proveedor no encontrado.");
        }
    }

    private static void buscarProductoPorNombre() {
        System.out.println("Ingrese el nombre del producto a buscar: ");
        String nombre = scanner.nextLine();

        Producto productoEncontrado = null;
        for (Producto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                productoEncontrado = producto;
                break;
            }
        }

        if (productoEncontrado != null) {
            System.out.println("Producto encontrado: " + productoEncontrado.imprimirDetalle());
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    private static void buscarSolicitudPorNumero() {
        System.out.println("Ingrese el número de la solicitud de compra a buscar: ");
        String numero = scanner.nextLine();

        SolicitudDeCompra solicitudEncontrada = null;
        for (SolicitudDeCompra solicitud : solicitudes) {
            if (solicitud.getNumero().equalsIgnoreCase(numero)) {
                solicitudEncontrada = solicitud;
                break;
            }
        }

        if (solicitudEncontrada != null) {
            System.out.println("Solicitud encontrada:");
            solicitudEncontrada.imprimir();
        } else {
            System.out.println("Solicitud no encontrada.");
        }
    }

    private static void aprobarRechazarSolicitud() {
        System.out.println("Ingrese el número de la solicitud a aprobar o rechazar: ");
        String numero = scanner.nextLine();

        SolicitudDeCompra solicitud = null;
        for (SolicitudDeCompra s : solicitudes) {
            if (s.getNumero().equalsIgnoreCase(numero)) {
                solicitud = s;
                break;
            }
        }

        if (solicitud != null) {
            System.out.println("Ingrese la acción (aprobar/rechazar): ");
            String accion = scanner.nextLine().toLowerCase();
            if (accion.equals("aprobar")) {
                solicitud.aprobar();
                System.out.println("Solicitud aprobada.");
            } else if (accion.equals("rechazar")) {
                solicitud.rechazar();
                System.out.println("Solicitud rechazada.");
            } else {
                System.out.println("Acción no válida.");
            }
        } else {
            System.out.println("Solicitud no encontrada.");
        }
    }

    private static void calcularTotalSolicitud() {
        System.out.println("Ingrese el número de la solicitud de compra: ");
        String numero = scanner.nextLine();

        SolicitudDeCompra solicitud = null;
        for (SolicitudDeCompra s : solicitudes) {
            if (s.getNumero().equalsIgnoreCase(numero)) {
                solicitud = s;
                break;
            }
        }

        if (solicitud != null) {
            double total = solicitud.calcularTotal();
            System.out.println("Total de la solicitud de compra: " + total);
        } else {
            System.out.println("Solicitud no encontrada.");
        }
    }
}
