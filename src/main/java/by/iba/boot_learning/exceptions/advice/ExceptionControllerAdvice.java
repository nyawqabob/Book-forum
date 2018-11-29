package by.iba.boot_learning.exceptions.advice;

import by.iba.boot_learning.exceptions.DaoException;
import by.iba.boot_learning.exceptions.NotValidDataException;
import by.iba.boot_learning.exceptions.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(DaoException.class)
    protected ResponseEntity<AdditionalException> handleDAOException(DaoException ex) {
        return new ResponseEntity<>(new AdditionalException(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ServiceException.class)
    protected ResponseEntity<AdditionalException> handleServiceException(ServiceException ex) {
        return new ResponseEntity<>(new AdditionalException(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotValidDataException.class)
    protected ResponseEntity<AdditionalException> handleNotValidDataException(NotValidDataException ex) {
        return new ResponseEntity<>(new AdditionalException(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }


}
