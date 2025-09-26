package org.purpura.apimg.exception.conversa;

import org.purpura.apimg.exception.base.DuplicateDataException;

import java.util.List;

public class ChatAlreadyExistsException extends DuplicateDataException {
    public ChatAlreadyExistsException(List<String> participants) {
        super("Um chat já existe para os participantes " + participants);
    }
}
