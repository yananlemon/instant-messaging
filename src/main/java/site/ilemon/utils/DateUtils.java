package site.ilemon.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p></p>
 *
 * @author Andy.Yan
 */
public class DateUtils {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    public static String format(Date date) {
        return sdf.format(date);
    }
}
