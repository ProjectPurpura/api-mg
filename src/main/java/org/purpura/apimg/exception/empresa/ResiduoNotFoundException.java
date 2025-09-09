package org.purpura.apimg.exception.empresa;

public class ResiduoNotFoundException extends EmpresaDataNotFoundException {
    public static final String RESIDUO = "resíduo";

    public ResiduoNotFoundException(String cnpj, String id) {
        super(RESIDUO, cnpj, id);
    }
}
