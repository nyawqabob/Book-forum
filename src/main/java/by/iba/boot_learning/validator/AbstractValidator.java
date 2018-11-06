package by.iba.boot_learning.validator;

import by.iba.boot_learning.validator.exception.NotValidDataException;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class AbstractValidator {

    public static void isValidByLength(String data, Integer minLength, Integer maxLength) {
        if (data.length() > maxLength || data.length() < minLength) {
            throw new NotValidDataException("Wrong length of " + data);
        }

    }

    public static void isValidByRegEx(String data, String regEx) {
        Pattern pattern = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(data);
        if (!matcher.find()) {
            throw new NotValidDataException("Wrong form of " + data);
        }

    }
}
