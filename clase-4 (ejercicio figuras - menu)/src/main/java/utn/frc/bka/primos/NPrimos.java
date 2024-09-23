package utn.frc.bka.primos;
import utn.frc.bka.util.MathUtil;

public class NPrimos {
    private static void primerosNPrimos (int cant) {
        int c = 0;
        int p = 2;
        System.out.println("----- PRIMEROS N NUMEROS PRIMOS ------");
        while (c < cant) {
            if (MathUtil.esPrimo(p)) {
                System.out.println(p + " es primo");
                c++;
            }
            p++;
        }
    }

    private static void primos1AlN (int cant) {
        int p = 2;
        System.out.println("----- NUMEROS PRIMOS DEL 1 AL N ------");
        while (p < cant) {
            if (MathUtil.esPrimo(p)) {
                System.out.println(p + " es primo");
                p++;
            }
            p++;
        }
    }


    public static void run() {
        primerosNPrimos(10);
        primos1AlN(10);
    }
}
