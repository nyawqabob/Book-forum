package by.iba.boot_learning.controller.advice;

import by.iba.boot_learning.exceptions.DaoException;
import by.iba.boot_learning.exceptions.NotValidDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.ws.rs.ext.Provider;

@Provider
@ControllerAdvice
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DaoException.class)
    protected ResponseEntity<AdditionalException> handleDAOException(DaoException ex) {
        return new ResponseEntity<>(new AdditionalException(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotValidDataException.class)
    protected ResponseEntity<AdditionalException> handleNotValidDataException(NotValidDataException ex) {
        return new ResponseEntity<>(new AdditionalException(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }



}
