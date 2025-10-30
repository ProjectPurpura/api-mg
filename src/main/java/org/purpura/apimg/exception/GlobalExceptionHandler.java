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
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(String.format("Erro interno do servidor, por favor contate os programadores de back-end: %s", ex.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body("Erro ao processar a solicitação: " + ex.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex) {
        return ResponseEntity.badRequest().body("Erro de validação: " + ex.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body("Formato da requisição JSON inválido: " + ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new LinkedHashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException notFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundException.getMessage());
    }

    @ExceptionHandler(EmpresaNotFoundException.class)
    public ResponseEntity<String> handleEmpresaNotFoundException(EmpresaNotFoundException empresaNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(empresaNotFoundException.getMessage());
    }

    @ExceptionHandler(DuplicateDataException.class)
    public ResponseEntity<String> handleDuplicateDataException(DuplicateDataException duplicateDataException) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(duplicateDataException.getMessage());
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<String> handleDuplicateKeyException(DuplicateKeyException duplicateKeyException) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(duplicateKeyException.getMessage());
    }

    @ExceptionHandler(ChatAlreadyExistsException.class)
    public ResponseEntity<String> handle(ChatAlreadyExistsException chatAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(chatAlreadyExistsException.getMessage());
    }

    @ExceptionHandler(ResiduoInsufficientStockException.class)
    public ResponseEntity<String> handle(ResiduoInsufficientStockException residuoInsufficientStockException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(residuoInsufficientStockException.getMessage());
    }
}
