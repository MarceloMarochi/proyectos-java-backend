package utn.frc.bka;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Clase para asignarle los valores obtenidos en el CSV a una instancia
class Product {

    // Cuando el tipo de dato empieza con mayúscula es de tipo envoltorio y aadmite nulos.
    Integer id;

    String nombre;

    Double price;

    // Constructor
    public Product(Integer id, String nombre, Double price) {
        this.id = id;
        this.nombre = nombre;
        this.price = price;
    }
}


public class Orders {

    // Parametros: el path, int desplazamiento (líneas iniciales que se desea saltar), int límite de líneas que se desea leer.
    private static List <String> readTxtFileLines(String txtFilePath, int offset, int limit) {
        // Se inicializa la lista dinámica lines
        List<String> lines = new ArrayList<>();

        // Se abre un bloque try-with-resources (try con recursos) que indica que el archivo se cerrará automáticamente ni bien salga del bloque try, aunque entre en una excepción.
        // Usamos BufferedReaader para leer el archivo
        try (BufferedReader reader = new BufferedReader(new FileReader( txtFilePath))) {
            String line;

            // reader.readLine() lee las lineas una a una
            // se detiene después de haber leído offset o de que no haya más lineas
            for (int cnt = 0; cnt < offset && (line = reader.readLine()) != null; cnt++);

            // lee las líneas una por una y las agrega a la lista lines. hasta el límite o hasta que no queden más.
            for (int cnt = 0; cnt < limit && (line = reader.readLine()) != null; cnt++) {
                lines.add(line);
            }

            // si existe un error se imprime el mensaje de error
        } catch (IOException ioe) {
            lines.clear();
            System.out.println("ERROR: " + ioe.getMessage());
        }
        // retorna la lista con las líneas
        return lines;
    }

    // FUNCIÓN DE SOPORTE: Para parsear fechas
    private static Date parseDate(String date, String format) throws ParseException {
        DateFormat df = new SimpleDateFormat(format);
        return df.parse(date);
    }

    public static void main(String[] args) {

        String fPath = System.getProperty("user.dir") + "\\Orders.csv";

        try {
            // Obtengo una lista de String con las lineas.
            List<String> lines = readTxtFileLines(fPath, 1, 10);

            // Itero la lista anterior
            for (String linea : lines) {

                // El parametro -1 indica que se dividirá la cadena en tantas partes como sea posible, no indica un límite.
                String[] values = linea.split(",", -1);

                Integer customerId = Integer.parseInt(values[0]);

                // trim() -> elimina los espacios en blanco al principio y al final de la cadena.
                String customerName = values[1].replace('"', ' ').trim();

                Date orderDate = parseDate(values[5].replace('"', ' ').trim(), "yyyy-MM-dd");

                System.out.println("ID: " + customerId + " --- NOMBRE: " + customerName + " --- FECHA: "+ orderDate);

                // Crear un objeto Producto con los datos obtenidos del CSV
                Product product = new Product(Integer.parseInt(values[6]), values[7], Double.parseDouble(values[9]));
            }
        } catch (ParseException e) {
            System.out.println("ERROR " + e.getMessage());
        }
    }
}
