package by.iba.boot_learning.validator.name;

import by.iba.boot_learning.validator.AbstractValidator;
import org.springframework.stereotype.Component;

@Component
public class NameValidator extends AbstractValidator {

    public NameValidator() {
        super("name");
    }
}
