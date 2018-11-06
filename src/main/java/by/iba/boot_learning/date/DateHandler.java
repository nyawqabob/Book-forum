package by.iba.boot_learning.date;

import by.iba.boot_learning.constants.date.DateConstants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class DateHandler {

    public static String getCurrentDay() {
        DateFormat dateFormat = new SimpleDateFormat(DateConstants.DATE_FORMAT);
        dateFormat.setTimeZone(TimeZone.getTimeZone(DateConstants.TIME_ZONE));
        Calendar calendar = Calendar.getInstance();
        String stringDate = dateFormat.format(calendar.getTime());
        return stringDate;
    }
}
