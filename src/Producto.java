import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Producto {
    String nombre;
    int cantidad;
    double precio;

    public static Producto buscar(String buscar) {
        Producto prod = new Producto();
        prod.nombre = buscar;
        prod.cantidad = 0;
        prod.precio = 0;

        File bdFile = new File("bd.csv");
        try (Scanner myReader = new Scanner(bdFile)) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String regex = "[,]";
                String[] myArray = data.split(regex);
                if (myArray[0].equals(buscar)) {
                    prod.nombre = myArray[0];
                    prod.cantidad = Integer.valueOf(myArray[1]);
                    prod.precio = Double.valueOf(myArray[2]);
                }
            }
            myReader.close();
        } catch (NumberFormatException | FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return prod;
    }

    public void guardar() {
        boolean escribio = false;
        File bdFile = new File("bd.csv");
        try (Scanner myReader = new Scanner(bdFile)) {
            String newBd = "";

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String regex = "[,]";
                String[] myArray = data.split(regex);
                if (myArray[0].equals(this.nombre)) {
                    newBd += this.nombre + "," + this.cantidad + "," + this.precio;
                    escribio = true;
                } else {
                    newBd += data;
                }
                if (myReader.hasNextLine()) {
                    newBd += "\n";
                } else if (!escribio) {
                    newBd += "\n" + this.nombre + "," + this.cantidad + "," + this.precio;
                }
            }
            myReader.close();

            bdFile.delete();
            try (FileWriter myWriter = new FileWriter("bd.csv")) {
                myWriter.write(newBd);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
