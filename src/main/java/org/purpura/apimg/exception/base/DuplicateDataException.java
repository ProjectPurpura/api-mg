package org.purpura.apimg.exception.base;

public class DuplicateDataException extends RuntimeException {
    public DuplicateDataException(String message) {
        super(String.format("Dados duplicados: %s", message));
    }
}
