package by.iba.boot_learning.date;

import by.iba.boot_learning.constants.ConstantHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class DateHandler {

    public static String getCurrentDay() {
        DateFormat dateFormat = new SimpleDateFormat(ConstantHelper.DATE_FORMAT);
        dateFormat.setTimeZone(TimeZone.getTimeZone(ConstantHelper.TIME_ZONE));
        Calendar calendar = Calendar.getInstance();
        return dateFormat.format(calendar.getTime());
    }
}
