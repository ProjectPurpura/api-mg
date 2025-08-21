package org.purpura.apimg.exception;

public class DocumentNotFoundException extends RuntimeException {
    public DocumentNotFoundException(String id) {
        super("Não foi possível encontrar documento: " + id);
    }
}
