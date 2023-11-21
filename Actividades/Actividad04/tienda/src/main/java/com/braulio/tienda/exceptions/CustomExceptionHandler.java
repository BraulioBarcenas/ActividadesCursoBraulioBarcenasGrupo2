package com.braulio.tienda.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import jakarta.persistence.EntityNotFoundException;

@ControllerAdvice
public class CustomExceptionHandler {
    

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        FieldError fieldError = ex.getBindingResult().getFieldError();
        String errorMessage = fieldError.getDefaultMessage();
        return new ResponseEntity<>(new ErrorResponse(status, errorMessage),status);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String tipoRequerido = ex.getRequiredType().getSimpleName();
        String parametro = ex.getParameter().getParameterName();
        String mensaje = "Ha ocurrido un problema, se esperaba un valor "+tipoRequerido+" en "+parametro;
        return new ResponseEntity<>(new ErrorResponse(status,mensaje),status);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        
        String mensaje = ex.getMessage();
        return new ResponseEntity<>(new ErrorResponse(status,mensaje),status);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(new ErrorResponse(status, ex.getMessage()), status);
    }

    @ExceptionHandler(OutOfStockException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleOutOfStockException(OutOfStockException ex) {
        HttpStatus status = HttpStatus.CONFLICT;
        return new ResponseEntity<>(new ErrorResponse(status, ex.getMessage()), status);
    }

    @ExceptionHandler(NullParamsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleNullParamsException(NullParamsException ex){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(new ErrorResponse(status, ex.getMessage()), status);
    }

    @ExceptionHandler(DupedEntityException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleDupedEntityException(DupedEntityException ex){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(new ErrorResponse(status, ex.getMessage()), status);
    }


    @ExceptionHandler(OwnStoreException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleOwnStoreException(OwnStoreException ex){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(new ErrorResponse(status, ex.getMessage()), status);
    }

    
}
