package utn.frc.bka;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArchivoCSV {
    // Parametros: el path, int desplazamiento (líneas iniciales que se desea saltar), int límite de líneas que se desea leer.
    private static List<String> readTxtFileLines(String txtFilePath, int offset, int limit) {
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

        // Listas que vamos a usar
        List<Product> products = new ArrayList<>();
        List<Client> clients = new ArrayList<>();
        List<City> citys = new ArrayList<>();
        List<Order> orders = new ArrayList<>();


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

                // System.out.println("ID: " + customerId + " --- NOMBRE: " + customerName + " --- FECHA: "+ orderDate);

                // Crear un objeto Producto con los datos obtenidos del CSV
                Product product = new Product(Integer.parseInt(values[6]), values[7], Integer.parseInt(values[8]),Double.parseDouble(values[9]));
                products.add(product);

                // Crear un objeto Producto con los datos obtenidos del CSV
                Client client = new Client(Integer.parseInt(values[0]), values[1]);
                clients.add(client);

                // Crear un objeto Producto con los datos obtenidos del CSV
                City city = new City(Integer.parseInt(values[2]), values[3]);
                citys.add(city);

                // Crear un objeto Producto con los datos obtenidos del CSV
                Order order = new Order(Integer.parseInt(values[4]), values[5]);
                orders.add(order);
            }

            System.out.println("---------ORDERS----------");
            for (Order order : orders) {
                System.out.println("ID: " + order.getId() + " - Date: " + order.getDate());
            }
            System.out.println("\n\n---------CLIENTS----------");
            for (Client client : clients) {
                System.out.println("ID: " + client.getClientId() + " - Name: " + client.getName());
            }
            System.out.println("\n\n---------PRODUCTS----------");
            for (Product product : products) {
                System.out.println("ID: " + product.getId() + " - Name: " + product.getNombre() + " - Count: " + product.getCount() + " - Price: " + product.getPrice());
            }
            System.out.println("\n\n---------CITYS----------");
            for (City city : citys) {
                System.out.println("ID: " + city.getCityId() + " - Name: " + city.getName());
            }

        } catch (ParseException e) {
            System.out.println("ERROR " + e.getMessage());
        }
    }
}

