package org.purpura.apimg.exception;

public class ChavePixNotFoundException extends EmpresaDataNotFoundException {
    public static final String CHAVE_PIX = "chave pix";

    public ChavePixNotFoundException(String cnpj, String chave) {
        super(CHAVE_PIX, cnpj, chave);
    }
}
