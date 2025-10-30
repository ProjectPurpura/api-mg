package org.purpura.apimg.exception.empresa;

public class ResiduoInsufficientStockException extends RuntimeException {
    public ResiduoInsufficientStockException(String idResiduo, Integer quantidade) {
        super(String.format("Não há estoque suficiente para o resíduo com id %s. Quantidade solicitada: %d", idResiduo, quantidade));
    }
}
