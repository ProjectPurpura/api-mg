package org.purpura.apimg.exception.empresa;

public class ChavePixNotFoundException extends EmpresaDataNotFoundException {
    public static final String CHAVE_PIX = "chave pix";

    public ChavePixNotFoundException(String cnpj, String id) {
        super(CHAVE_PIX, cnpj, id);
    }
}
