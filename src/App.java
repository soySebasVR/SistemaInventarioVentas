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
                System.out.println("4. Salir");
                opcion = sc.nextLine();

                if (opcion.equals("1")) {
                    Venta();
                    TimeUnit.SECONDS.sleep(1);
                } else if (opcion.equals("2")) {
                    NuevoProducto();
                    TimeUnit.SECONDS.sleep(1);
                } else if (opcion.equals("4")) {
                    System.out.println("Muchas Gracias");
                    System.exit(0);
                } else {
                    System.out.println("El número ingresado no es válido");
                }
            }
        }
    }

    public static void Venta() {
        double total = 0;
        String opcion;
        String nombre;
        Producto prod;
        int cantidad;

        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.println("Qué desea hacer?");
                System.out.println("1. Ingresar Producto");
                System.out.println("2. Cierre venta");
                opcion = sc.nextLine();

                if (opcion.equals("1")) {
                    System.out.println("Ingresar el nombre del producto:");
                    nombre = sc.nextLine();
                    prod = Producto.buscar(nombre);
                    if (prod.cantidad == 0) {
                        System.out.println("El producto no existe");
                        continue;
                    }
                    System.out.println("Ingresa la cantidad:");
                    try {
                        cantidad = Integer.valueOf(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("La cantidad no es valida");
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
                } else {
                    System.out.println("El número ingresado no es válido");
                }
            }
        }
    }
    public static void NuevoProducto() {
        
    }
}
