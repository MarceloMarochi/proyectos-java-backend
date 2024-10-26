package utn.frc.bda.semana02;

import utn.frc.bda.util.MathUtil;

public class NPrimos {

    private static void firstNPrimes(int n) {
        if (n < 1) {
            return;
        }

        for (int c = 0, p = 1; c < n; p++) {
            if (MathUtil.isPrime(p)) {
                System.out.println(p + " es primo");
                c++;
            }
        }

        //int c = 0;
        //int p = 1;
        //
        //while (c < n) {
        //    if (MathUtil.isPrime(p)) {
        //        System.out.println(p + " es primo");
        //        c++;
        //    }
        //    p++;
        //}
    }

    public static void run() {
        firstNPrimes(100);
    }

    public static void main(String[] args) {
        run();
    }

}
