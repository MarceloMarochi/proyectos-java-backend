package utn.frc.bda;

import utn.frc.bda.semana02.MultiplosEnVector;
import utn.frc.bda.semana02.NPrimos;
import utn.frc.bda.semana02.Figuras;
import utn.frc.bda.semana02.ISBN;
import utn.frc.bda.util.menu.Menu;
import utn.frc.bda.util.menu.MenuItem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        //MenuItem mI1 = new MenuItem("key1", "título 1");
        //MenuItem mI2 = new MenuItem("key2", "acción 2");
        //
        //Menu m = new Menu();



        //int op = 0;
        //
        //while (op != 5) {
        //    System.out.println("Ingrese una opción:");
        //    System.out.println("1. Múltiplos en Vector");
        //    System.out.println("2. Nros Primos");
        //    System.out.println("3. Figuras (ciclos for)");
        //    System.out.println("4. ISBN");
        //    System.out.println("5. Salir");
        //
        //    try {
        //        op = sc.nextInt();
        //    } catch (Exception e) {
        //        sc = new Scanner(System.in);
        //        op = 0;
        //    }
        //
        //    switch (op) {
        //        case 1:
        //            MultiplosEnVector.run();
        //            break;
        //        case 2:
        //            NPrimos.run();
        //            break;
        //        case 3:
        //            Figuras.run();
        //            break;
        //        case 4:
        //            ISBN.run();
        //            break;
        //        case 5:
        //            break;
        //        default:
        //            System.out.println("Debe ingresar un entero entre 1 y 5");
        //            break;
        //    }
        //
        //}

        sc.close();
    }
}
