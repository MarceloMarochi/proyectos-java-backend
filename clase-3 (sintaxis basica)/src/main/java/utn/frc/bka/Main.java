package utn.frc.bka;

public class Main {

    private static boolean isMultipleOf(int mult, int n) {
        return mult % n == 0;
    }

    private static boolean esPrimo(int n) {
        for (int i = 2, c =0; i <= (n / 2); i++) {
            if (isMultipleOf(n, i)) {
                return false;
            }
        }
        return true;
    }


    private static void primerosNPrimos (int cant) {
        int c = 0;
        int p = 2;
        System.out.println("----- PRIMEROS N NUMEROS PRIMOS ------");
        while (c < cant) {
            if (esPrimo(p)) {
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
            if (esPrimo(p)) {
                System.out.println(p + " es primo");
                p++;
            }
            p++;
        }
    }


    public static void main(String[] args) {
        primerosNPrimos(10);
        primos1AlN(10);
    }
}