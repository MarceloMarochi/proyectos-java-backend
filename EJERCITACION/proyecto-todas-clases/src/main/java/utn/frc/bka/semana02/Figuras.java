package utn.frc.bka.semana02;

import utn.frc.bka.util.MathUtil;

public class Figuras {

    private static final char C = '*';
    private static final char S = ' ';

    private static void printHeader(String title) {
        System.out.println("----------------------------------------");
        System.out.println(title);
        System.out.println("----------------------------------------");
    }

    private static void fig1() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(C);
                System.out.print(S);
            }
            System.out.println();
        }
    }
    private static void fig1Alt1() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(C);
                System.out.print(S);
            }
            System.out.println(C);
        }
    }

    private static void fig1Alt2() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 11; j++) {
                System.out.print(MathUtil.isEven(j) ? C : S);
            }
            System.out.println();
        }
    }

    private static void fig2() {
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

    private static void fig2Alt() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 12; j++) {
                System.out.print(MathUtil.isEven(i+j) ? C : S);
            }
            System.out.println();
        }
    }

    private static void fig3() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print(C);
                System.out.print(S);
            }
            System.out.println();
        }
    }

    private static void fig3Alt() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 2*i; j++) {
                System.out.print(MathUtil.isEven(j) ? C : S);
            }
            System.out.println(C);
        }
    }

    private static void fig4() {
        int a = 0;

        for (int i = 0; i < 10; i++) {
            if (i > 4) {
                a--;
            } else {
                a++;
            }

            for (int j = 1; j <= a; j++) {
                System.out.print(C);
                System.out.print(S);
            }

            System.out.println();
        }
    }

    private static void fig4Alt() {
        int lines = 9, mid = lines / 2;

        for (int i = 0; i < lines; i++) {
            int a = i < mid ? i : lines - i - 1;
            for (int j = 0; j < 2 * a; j++) {
                System.out.print(MathUtil.isEven(j) ? C : S);
            }
            System.out.println(C);
        }
    }

    public static void run() {
        printHeader("Fig 1");
        fig1();
        printHeader("Fig 1 Alt 1");
        fig1Alt1();
        printHeader("Fig 1 Alt 2");
        fig1Alt2();
        printHeader("Fig 2");
        fig2();
        printHeader("Fig 2 Alt");
        fig2Alt();
        printHeader("Fig 3");
        fig3();
        printHeader("Fig 3 Alt");
        fig3Alt();
        printHeader("Fig 4");
        fig4();
        printHeader("Fig 4 Alt");
        fig4Alt();
    }

    public static void main(String[] args) {
        run();
    }

}
