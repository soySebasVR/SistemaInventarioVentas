public class Producto {
    int id;
    String nombre;
    int cantidad;
    double precio;

    public static Producto getById(int search) {
        Producto prod = new Producto();
        return prod;
    }

    public static Producto getByNombre(String nombre) {
        Producto prod = new Producto();
        return prod;
    }
}
