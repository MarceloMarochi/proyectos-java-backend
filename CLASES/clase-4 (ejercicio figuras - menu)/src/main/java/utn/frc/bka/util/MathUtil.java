package utn.frc.bka.util;

public abstract class MathUtil {

    public static boolean isMultipleOf(int mult, int n) {
        return mult % n == 0;
    }

    public static boolean isEven(int n) {

        return n == 0
                ? true
                : isMultipleOf(n, 2);
    }

    public static boolean isOdd(int n) {
        return !isEven(n);
    }

    public static boolean esPrimo(int n) {
        for (int i = 2, c =0; i <= (n / 2); i++) {
            if (isMultipleOf(n, i)) {
                return false;
            }
        }
        return true;
    }

}
