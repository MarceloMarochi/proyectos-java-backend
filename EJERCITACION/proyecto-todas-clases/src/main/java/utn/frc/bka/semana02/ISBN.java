package utn.frc.bka.semana02;

import utn.frc.bka.util.MathUtil;

public class ISBN {

    private static boolean isISBNValid(String code) {
        int len = code.length();
        if (len != 13) {
            return false;
        }

        int sum = 0, mult = 10;
        for (char c : code.toCharArray()) {
            sum += c == '-' ? 0 : mult-- * Character.getNumericValue(c);
        }

        return mult == 0 && MathUtil.isMultipleOf(sum, 11);
    }

    public static void run() {
        String[] codes = {
                "0-306-40615-2",
                "84-8181-227-7",
                "84-8181-227-1",
                "1-55615-507-7",
                "1-55615-507-9",
                "950-07-2749-8",
        };

        for (String code : codes) {
            String valid = isISBNValid(code) ? "válido" : "inválido";
            System.out.printf("%s \t %s \n", code, valid);
        }
    }

    public static void main(String[] args) {
        run();
    }
}
