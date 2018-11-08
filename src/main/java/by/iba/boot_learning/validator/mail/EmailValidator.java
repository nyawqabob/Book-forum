package by.iba.boot_learning.validator.mail;

import by.iba.boot_learning.validator.AbstractValidator;
import org.springframework.stereotype.Component;

@Component
public class EmailValidator extends AbstractValidator {

    public EmailValidator() {
        super("email");
    }
}

