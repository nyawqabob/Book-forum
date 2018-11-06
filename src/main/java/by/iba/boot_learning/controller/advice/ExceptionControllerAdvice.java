package by.iba.boot_learning.controller.advice;

import by.iba.boot_learning.dao.exception.DaoException;
import by.iba.boot_learning.properties.exception.PropertyException;
import by.iba.boot_learning.validator.exception.NotValidDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DaoException.class)
    protected ResponseEntity<AdditionalException> handleDAOException(DaoException ex) {
        ResponseEntity<AdditionalException> responseEntity = new ResponseEntity<>(new AdditionalException(ex.getMessage()), HttpStatus.NOT_FOUND);
        return responseEntity;
    }

    @ExceptionHandler(NotValidDataException.class)
    protected ResponseEntity<AdditionalException> handleNotValidDataException(NotValidDataException ex) {
        ResponseEntity<AdditionalException> responseEntity = new ResponseEntity<>(new AdditionalException(ex.getMessage()), HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(PropertyException.class)
    protected ResponseEntity<AdditionalException> handlePropertyException(PropertyException ex) {
        ResponseEntity<AdditionalException> responseEntity = new ResponseEntity<>(new AdditionalException(ex.getMessage()), HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

}
