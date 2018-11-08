package by.iba.boot_learning.validator;

import by.iba.boot_learning.exceptions.NotValidDataException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AbstractValidator {

    private final String fieldType;

    public AbstractValidator(String fieldType) {
        this.fieldType = fieldType;
    }

    public void isValidByLength(String data, int minLength, int maxLength) {
        if (data.length() > maxLength || data.length() < minLength) {
            throw new NotValidDataException("Wrong length of " + fieldType + ". Your value: " + data);
        }

    }

    public void isValidByRegEx(String data, String regEx) {
        Pattern pattern = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(data);
        if (!matcher.find()) {
            throw new NotValidDataException("Wrong form of " + fieldType + ". Your value: " + data);
        }

    }
}
