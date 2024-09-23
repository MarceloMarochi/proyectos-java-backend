package utn.frc.bka;
import utn.frc.bka.figuras.Figuras;
import utn.frc.bka.isbn.Isbn;
import utn.frc.bka.primos.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        while (true) {

            System.out.println("Ingrese una opcion:");
            System.out.println("1. Nros Primos");
            System.out.println("2. Figuras");
            System.out.println("3. ISBN");
            System.out.println("4. Salir");

            try {
                opcion = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Debe ingresar entre 1 y 4");
                sc = new Scanner(System.in);
                opcion = 0;

            }

            switch (opcion) {
                case 1:
                    NPrimos.run();
                    break;
                case 2:
                    Figuras.run();
                    break;
                case 3:
                    Isbn.run();
                    break;
                case 4:
                    sc.close();
                    return;
                default:
                    System.out.println("Opcion incorrecta.");

            }
        }
        // sc.close();
    }
}