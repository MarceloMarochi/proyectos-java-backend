package utn.frc.bka.util;

/**
 *
 * @author scarafia
 */
public abstract class StringUtil {

    public static String lPad(String text, int len) {
        return String.format("%" + len + "s", text);
    }

    public static String lPad(Object o, int len) {
        return lPad(o.toString(), len);
    }

    public static String lPad(Object o, int len, int margin) {
        return lPad(o + fill(' ', margin), len);
    }

    public static String lPad(String text, int len, int margin, int padding, char fillCharacter) {
        return
                fill(fillCharacter, len-text.length()-2*padding-margin)
                        + fill(' ', padding)
                        + text
                        + fill(' ', padding)
                        + fill(fillCharacter, margin);
    }

    public static String lPad(Object o, int len, int margin, int padding, char fillCharacter) {
        return lPad(o.toString(), len, margin, padding, fillCharacter);
    }

    public static String rPad(String text, int len) {
        return String.format("%-" + len + "s", text);
    }

    public static String rPad(Object o, int len) {
        return rPad(o.toString(), len);
    }

    public static String rPad(Object o, int len, int margin) {
        return rPad(fill(' ', margin) + o, len);
    }

    public static String rPad(String text, int len, int margin, int padding, char fillCharacter) {
        return
                fill(fillCharacter, margin)
                        + fill(' ', padding)
                        + text
                        + fill(' ', padding)
                        + fill('=', len-text.length()-2*padding-margin);
    }

    public static String rPad(Object o, int len, int margin, int padding, char fillCharacter) {
        return rPad(o.toString(), len, margin, padding, fillCharacter);
    }

    public static String fill(char caracter, int len) {
        return lPad("", len).replace(' ', caracter);
    }

    public static String mirror(String s) {
        //if (true) return new StringBuilder(s).reverse().toString();

        StringBuilder builder = new StringBuilder();

        int l = s.length();
        for (int i = l-1; i >= 0; i--) {
            builder.append(s.charAt(i));
        }

        return builder.toString();
    }

}
