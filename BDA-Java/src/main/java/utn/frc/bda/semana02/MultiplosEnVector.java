package utn.frc.bda.semana02;

import utn.frc.bda.util.MathUtil;
import java.util.Arrays;

public class MultiplosEnVector {

    private static void printMultiplos(int[] v) {
        // El vector se asume ordenado... y el 0 (cero)?
        System.out.println("Para " + Arrays.toString(v));

        int len = v.length;

        for (int i = 0; i < len; i++) {
            System.out.print("Son múltiplos de " + v[i] + ": " + v[i]);
            for (int j = i+1; j < len; j++) {
                if (MathUtil.isMultipleOf(v[j], v[i])) {
                    System.out.print(", " + v[j]);
                }
            }
            System.out.println();
        }
    }

    public static void run() {
        int[] numeros = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        int[][] array = {
                {1, 2, 3, 4, 5, 6, 7, 8, 9},
                {0, 3, 4, 6, 8, 9},
                {11, 18, 27, 36, 55},
                {4, 9, 12, 27, 36}
        };

        for (int[] vector : array) {
            printMultiplos(vector);
        }

    }

    public static void main(String[] args) {
        run();
    }
}
