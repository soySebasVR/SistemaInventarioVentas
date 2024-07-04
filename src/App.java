import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) throws Exception {
        String opcion;
        System.out.println("Bienvenido al sistema de ventas");
        TimeUnit.SECONDS.sleep(1);

        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.println("Qué desea hacer?");
                System.out.println("1. Ingresar venta");
                System.out.println("2. Ingresar producto");
                System.out.println("4. Salir");
                opcion = sc.nextLine();

                if (opcion.equals("1")) {
                    Venta();
                    TimeUnit.SECONDS.sleep(1);
                } else if (opcion.equals("2")) {
                    NuevoProducto();
                    TimeUnit.SECONDS.sleep(1);
                } else if (opcion.equals("4")) {
                    System.out.println("Gracias");
                    System.exit(0);
                } else {
                    System.out.println("Número ingresado no válido");
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
                } else {
                    System.out.println("Número ingresado no válido");
                }
            }
        }
    }
    public static void NuevoProducto() {
        
    }
}
