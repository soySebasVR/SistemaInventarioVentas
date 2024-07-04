import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) throws Exception {
        String opcion;
        System.out.println("Bienvenido al Sistema de Ventas de la Bodega San Judas");
        TimeUnit.SECONDS.sleep(1);

        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.println("¿Qué desea hacer?");
                System.out.println("1. Ingresar venta");
                System.out.println("2. Ingresar un producto");
                System.out.println("3. Salir");
                opcion = sc.nextLine();

                if (opcion.equals("1")) {
                    Venta(sc);
                    TimeUnit.SECONDS.sleep(1);
                } else if (opcion.equals("2")) {
                    NuevoProducto(sc);
                    TimeUnit.SECONDS.sleep(1);
                } else if (opcion.equals("3")) {
                    System.out.println("Muchas Gracias");
                    System.exit(0);
                } else {
                    System.out.println("El número ingresado no es válido");
                }
            }
        }
    }

    public static void Venta(Scanner sc) {
        double total = 0;
        String opcion;
        String nombre;
        Producto prod;
        int cantidad;

        while (true) {
            System.out.println("Qué desea hacer?");
            System.out.println("1. Ingresar Producto");
            System.out.println("2. Cierre venta");
            opcion = sc.nextLine();

            if (opcion.equals("1")) {
                System.out.println("Ingresar nombre:");
                nombre = sc.nextLine();
                prod = Producto.buscar(nombre);
                if (prod.cantidad == 0) {
                    System.out.println("Producto no existe");
                    continue;
                }
                System.out.println("Ingresar cantidad:");
                try {
                    cantidad = Integer.valueOf(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Cantidad no valida");
                    continue;
                }
                if (cantidad > prod.cantidad) {
                    System.out.println("No hay la cantidad ingresada");
                    continue;
                }
                total += prod.precio * cantidad;
                prod.cantidad = prod.cantidad - cantidad;
                prod.guardar();
            } else if (opcion.equals("2")) {
                System.out.println("CIERRE VENTA");
                System.out.printf("Boleta total: %.2f soles\n\n", total);
                break;
            } else {
                System.out.println("Número ingresado no válido");
            }
        }
    }

    public static void NuevoProducto(Scanner sc) {
        String opcion;
        Producto prod;
        String nombre;
        int cantidad;
        String cantidadStr;
        double precio;
        String precioStr;

        while (true) {
            System.out.println("Qué desea hacer?");
            System.out.println("1. Ingresar Producto");
            System.out.println("2. Cierre");
            opcion = sc.nextLine();

            if (opcion.equals("1")) {
                System.out.println("Ingresar nombre:");
                nombre = sc.nextLine();
                prod = Producto.buscar(nombre);
                System.out.println("Ingresar cantidad nueva (Sin valor para mantener)");
                cantidadStr = sc.nextLine();
                if (!cantidadStr.equals("")) {
                    cantidad = Integer.valueOf(cantidadStr);
                    prod.cantidad = prod.cantidad + cantidad;
                }
                System.out.println("Ingresar nuevo precio (Sin valor para mantener)");
                precioStr = sc.nextLine();
                if (!precioStr.equals("")) {
                    precio = Integer.valueOf(precioStr);
                    prod.precio = precio;
                }
                prod.guardar();
            } else if (opcion.equals("2")) {
                System.out.println("CIERRE");
                break;
            } else {
                System.out.println("Número ingresado no válido");
            }
        }
    }
}
