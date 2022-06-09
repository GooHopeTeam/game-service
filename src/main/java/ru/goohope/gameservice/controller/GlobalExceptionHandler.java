package ru.goohope.gameservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.goohope.gameservice.dto.error.ApiError;
import ru.goohope.gameservice.dto.error.ValidationError;
import ru.goohope.gameservice.exception.PublisherAlreadyExistsException;
import ru.goohope.gameservice.exception.UndefinedSortFieldException;

import javax.validation.ConstraintViolationException;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ValidationError> handleConstraintViolationException(ConstraintViolationException exception) {
        ValidationError validationError = new ValidationError();
        for (var violation : exception.getConstraintViolations()) {
            String queryParamPath = violation.getPropertyPath().toString();
            String queryParam = queryParamPath.contains(".") ?
                    queryParamPath.substring(queryParamPath.indexOf(".") + 1) :
                    queryParamPath;
            String constraintName = violation.getConstraintDescriptor().getAnnotation()
                    .annotationType().getSimpleName();
            validationError.addDetail(queryParam, ValidationError.getConstraintDescription(constraintName));
        }
        return new ResponseEntity<>(validationError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Void> handleNoSuchElementFoundException(NoSuchElementException exception) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UndefinedSortFieldException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> handleUndefinedSortFieldException(UndefinedSortFieldException exception) {
        return new ResponseEntity<>(new ApiError("undefined_sort_field"), HttpStatus.BAD_REQUEST);
    }

}
