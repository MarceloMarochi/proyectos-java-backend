package utn.frc.bda.semana04;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

class Product {
    Integer id;
    String nombre;
    Double price;

    public Product(Integer id, String nombre, Double price) {
        this.id = id;
        this.nombre = nombre;
        this.price = price;
    }
}

public class Orders {

    private static List<String> readTxtFileLinesWithScanner(String txtFilePath) {
        return readTxtFileLinesWithScanner(txtFilePath, 0, Integer.MAX_VALUE);
    }

    private static List<String> readTxtFileLinesWithScanner(String txtFilePath, int offset, int limit) {
        List<String> lines = new ArrayList<>();
        Scanner sc = null;

        try {
            File file = new File(txtFilePath);
            sc = new Scanner(file);

            for (int cnt = 0; cnt < offset && sc.hasNextLine(); cnt++) {
                sc.nextLine();
            }

            for (int cnt = 0; cnt < limit && sc.hasNextLine(); cnt++) {
                lines.add(sc.nextLine());
            }
        } catch (FileNotFoundException fnfe) {
            lines.clear();
            System.out.println("ERROR: " + fnfe.getMessage());
        } finally {
            if (sc != null) {
                sc.close();
            }
        }

        return lines;
    }

    private static List<String> readTxtFileLines(String txtFilePath) {
        return readTxtFileLines(txtFilePath, 0, Integer.MAX_VALUE);
    }

    private static List<String> readTxtFileLines(String txtFilePath, int offset, int limit) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(txtFilePath))) {
            String line;

            for (int cnt = 0; cnt < offset && (line = reader.readLine()) != null; cnt++);

            for (int cnt = 0; cnt < limit && (line = reader.readLine()) != null; cnt++) {
                lines.add(line);
            }
        } catch (IOException ioe) {
            lines.clear();
            System.out.println("ERROR: " + ioe.getMessage());
        }

        return lines;
    }

    private static Date parseDate(String date, String format) throws ParseException {
        DateFormat df = new SimpleDateFormat(format);
        return df.parse(date);
    }

    public static void main(String[] args) {
        //System.out.println("Working Directory = " + System.getProperty("user.dir")); if (true) return;
        String fPath = System.getProperty("user.dir") + "/src/main/java/utn/frc/bda/semana04/orders.csv";

        try {
            //List<String> lines = readTxtFileLinesWithScanner(fPath, 1, Integer.MAX_VALUE);
            List<String> lines = readTxtFileLines(fPath, 1, 5);

            for (String line : lines) {
                //System.out.println(line); if (true) continue;

                String[] values = line.split(",", -1); // for trailing "," (missing fields)

                Integer customerID = Integer.parseInt(values[0]);

                String customerName = values[1].replace('"', ' ').trim();
                //String customerName = values[1].replaceAll("\"", "").trim();

                Date orderDate = parseDate(values[5].replace('"', ' ').trim(), "yyyy-MM-dd");
                //Date orderDate = parseDate(values[5].replaceAll("\"", "").trim(), "yyyy-MM-dd");

                System.out.println("cid: " + customerName + ", name: " + customerName + ", date: " + orderDate);

                // build object
                //ClassName objectName = new ClassName(field0, field1, field2);


                // add to list
                //r.add(objectName);
            }
        } catch (ParseException pe) {
            System.out.println("ERROR: " + pe.getMessage());
        }

    }
}
