package by.iba.boot_learning.date;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class DateHandler {

    public static String getCurrentDay() {
        ZonedDateTime zonedDateTime = Instant.now().atZone(ZoneOffset.UTC);
        return zonedDateTime.toInstant().toString();

    }
}

