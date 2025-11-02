package org.purpura.apimg.exception;

import com.mongodb.DuplicateKeyException;
import jakarta.validation.ConstraintViolationException;
import org.purpura.apimg.exception.base.DuplicateDataException;
import org.purpura.apimg.exception.base.NotFoundException;
import org.purpura.apimg.exception.conversa.ChatAlreadyExistsException;
import org.purpura.apimg.exception.empresa.EmpresaNotFoundException;
import org.purpura.apimg.exception.empresa.ResiduoInsufficientStockException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleRuntimeException(RuntimeException ex) {
        return String.format("Erro interno do servidor, por favor contate os programadores de back-end: %s", ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIllegalArgumentException(IllegalArgumentException ex) {
        return "Erro ao processar a solicitação: " + ex.getMessage();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleConstraintViolationException(ConstraintViolationException ex) {
        return "Erro de validação: " + ex.getMessage();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return "Formato da requisição JSON inválido: " + ex.getMessage();
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public String handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
        return "Tipo de mídia não suportado: " + ex.getMessage();
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new LinkedHashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        return errors;
    }

    @ExceptionHandler(ResiduoInsufficientStockException.class)
    public ResponseEntity<String> handle(ResiduoInsufficientStockException residuoInsufficientStockException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(residuoInsufficientStockException.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(NotFoundException notFoundException) {
        return notFoundException.getMessage();
    }

    @ExceptionHandler(DuplicateDataException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleDuplicateDataException(DuplicateDataException duplicateDataException) {
        return duplicateDataException.getMessage();
    }

    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleDuplicateKeyException(DuplicateKeyException duplicateKeyException) {
        return duplicateKeyException.getMessage();
    }

}
