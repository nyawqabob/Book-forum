package by.iba.boot_learning.validator.mail;

import by.iba.boot_learning.validator.AbstractValidator;

public class EmailValidator extends AbstractValidator {
    {
        FIELD_TYPE = "email";
    }

    public void isValidEmailByRegEx(String data, String regEx) {
        isValidByRegEx(data, regEx, FIELD_TYPE);
    }

    public void isValidEmailByLength(String data, int minLength, int maxLength, String fieldType) {
        isValidByLength(data, minLength, maxLength, FIELD_TYPE);
    }
}

