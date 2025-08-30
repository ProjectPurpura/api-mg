package org.purpura.apimg.exception.empresa;

public class EnderecoNotFoundException extends EmpresaDataNotFoundException {
    public static final String ENDERECO = "endereço";

    public EnderecoNotFoundException(String cnpj, String id) {
        super(ENDERECO, cnpj, id);
    }
}
