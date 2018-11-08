package by.iba.boot_learning.validator;

import by.iba.boot_learning.exceptions.NotValidDataException;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class AbstractValidator {

    protected String FIELD_TYPE;

    protected void isValidByLength(String data, int minLength, int maxLength, String fieldType) {
        if (data.length() > maxLength || data.length() < minLength) {
            throw new NotValidDataException("Wrong length of "+fieldType+". Your value: " + data);
        }

    }

    protected void isValidByRegEx(String data, String regEx, String fieldType) {
        Pattern pattern = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(data);
        if (!matcher.find()) {
            throw new NotValidDataException("Wrong form of "+fieldType+". Your value: " + data);
        }

    }
}
