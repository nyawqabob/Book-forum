package by.iba.boot_learning.validator.date;

import by.iba.boot_learning.constants.date.DateConstants;
import by.iba.boot_learning.validator.exception.NotValidDataException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class DateValidator {
    public static final Logger logger = LogManager.getLogger(DateValidator.class);

    public static void isValid(String date) throws NotValidDataException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DateConstants.DATE_FORMAT);
        dateFormat.setTimeZone(TimeZone.getTimeZone(DateConstants.TIME_ZONE));
        try {
            dateFormat.parse(date);
        } catch (ParseException e) {
            throw new NotValidDataException(e.getMessage(), e);
        }
    }
}
