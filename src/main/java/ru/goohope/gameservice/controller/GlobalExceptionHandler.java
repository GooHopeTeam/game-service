package ru.goohope.gameservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
    public ResponseEntity<ValidationError> handleConstraintViolationException(ConstraintViolationException exception) {
        ValidationError validationError = new ValidationError();
        for (var violation : exception.getConstraintViolations()) {
            String queryParamPath = violation.getPropertyPath().toString();
            String queryParam = queryParamPath.contains(".") ?
                    queryParamPath.substring(queryParamPath.indexOf(".") + 1) :
                    queryParamPath;
            String constraintName = violation.getConstraintDescriptor().getAnnotation()
                    .annotationType().getSimpleName();
            validationError.addDetail(queryParam, ValidationError.getDetailByConstraint(constraintName));
        }
        return new ResponseEntity<>(validationError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Void> handleNoSuchElementFoundException(NoSuchElementException exception) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UndefinedSortFieldException.class)
    public ResponseEntity<ApiError> handleUndefinedSortFieldException(UndefinedSortFieldException exception) {
        return new ResponseEntity<>(new ApiError("undefined_sort_field"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ValidationError> handleException(MethodArgumentNotValidException exception) {
        ValidationError validationError = new ValidationError();
        for (var fieldError : exception.getBindingResult().getFieldErrors()) {
            validationError.addDetail(fieldError.getField(), ValidationError.getDetailByConstraint(fieldError.getCode()));
        }
        return new ResponseEntity<>(validationError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception exception) {
        return new ResponseEntity<>(new ApiError("undefined"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
