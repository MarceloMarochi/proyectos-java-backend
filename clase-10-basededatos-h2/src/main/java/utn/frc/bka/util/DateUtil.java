package utn.frc.bka.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author scarafia
 */
public abstract class DateUtil {

    public static final String ISO_DATE_FORMAT = "yyyy-MM-ddTHH:mm:ss";
    public static final String SHORT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String LONG_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String format(Date date, String format) {
        DateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    public static String toCustomDateFormat(Date date, String format) {
        return format(date, format);
    }

    public static String toISODateFormat(Date date) {
        return format(date, ISO_DATE_FORMAT);
    }

    public static String toShortDateFormat(Date date) {
        return format(date, SHORT_DATE_FORMAT);
    }

    public static String toLongDateFormat(Date date) {
        return format(date, LONG_DATE_FORMAT);
    }

    public static Date parse(String date, String format) throws ParseException {
        DateFormat df = new SimpleDateFormat(format);
        return df.parse(date);
    }

    public static Date fromCustomDateFormat(String date, String format) throws ParseException {
        return parse(date, format);
    }

    public static Date fromISODateFormat(String date) throws ParseException {
        return parse(date, ISO_DATE_FORMAT);
    }

    public static Date fromShortDateFormat(String date) throws ParseException {
        return parse(date, SHORT_DATE_FORMAT);
    }

    public static Date fromLongDateFormat(String date) throws ParseException {
        return parse(date, LONG_DATE_FORMAT);
    }

}
