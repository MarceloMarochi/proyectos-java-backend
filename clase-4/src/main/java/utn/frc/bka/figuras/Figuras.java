package utn.frc.bka.figuras;

import utn.frc.bka.util.MathUtil;

public class Figuras {

    private static final char C = '*';
    private static final char S = ' ';


    private static void figura1() {
        int i = 0;
        int j = 0;
        System.out.println("Figura 1");

        while (j < 4) {
            while (i < 5) {
                System.out.print(C);
                System.out.print(S);
                i++;
            }
            System.out.println(C);
            j++;
            i = 0;
        }
    }

    private static void figura2() {
        System.out.println("Figura 2");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                if (MathUtil.isEven(i)) {
                    System.out.print(C);
                    System.out.print(S);
                } else {
                    System.out.print(S);
                    System.out.print(C);
                }

            }
            System.out.println();
        }
    }

    private static void figura3() {
        System.out.println("Figura 3");
        for (int i = 1; i < 6; i++) {
            for (int j = 1; j <= i; j++) {
               System.out.print(C);
                System.out.print(S);
            }
            System.out.println();
        }
    }

    private static void figura4() {
        System.out.println("Figura 4");
        int a = 0;
        for (int i = 0; i < 10; i++) {
            a++;

            for (int j = 1; j <= a; j++) {
                System.out.print(C);
                System.out.print(S);
            }
            if (i > 3) {
                a-=2;
            }
            System.out.println();

        }
    }

    public static void run() {

        figura1();
        figura2();
        figura3();
        figura4();
    }
}
