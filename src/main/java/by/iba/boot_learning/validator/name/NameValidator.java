package by.iba.boot_learning.validator.name;

import by.iba.boot_learning.validator.AbstractValidator;

public class NameValidator extends AbstractValidator {
    {
        FIELD_TYPE = "name";
    }

    public void isValidNameByRegEx(String data, String regEx) {
        isValidByRegEx(data, regEx, FIELD_TYPE);
    }

    public void isValidNameByLength(String data, int minLength, int maxLength, String fieldType) {
        isValidByLength(data, minLength, maxLength, FIELD_TYPE);
    }

}
