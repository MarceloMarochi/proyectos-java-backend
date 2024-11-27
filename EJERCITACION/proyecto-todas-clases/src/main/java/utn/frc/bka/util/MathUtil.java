package utn.frc.bka.util;

public abstract class MathUtil {

    public static boolean isMultipleOf(int mult, int n) {
        return n != 0 && mult % n == 0;
    }

    public static boolean isEven(int n) {
        return isMultipleOf(n, 2);
    }

    public static boolean isOdd(int n) {
        return !isEven(n);
    }

    public static boolean isPrime(int n) {
        if (n <= 1) { return false; }

        for (int i = 1, c = 0; i <= n/2; i++) {
            if (isMultipleOf(n, i)) {
                if (++c == 2) {
                    return false;
                }
            }
        }

        return true;
    }

}
