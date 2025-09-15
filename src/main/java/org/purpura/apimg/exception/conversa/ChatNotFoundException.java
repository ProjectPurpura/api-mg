package org.purpura.apimg.exception.conversa;

import org.purpura.apimg.exception.base.NotFoundException;

public class ChatNotFoundException extends NotFoundException {
    public ChatNotFoundException(String chatId) {
        super(String.format("Não foi possível encontrar a conversa com id %s", chatId));
    }
}
