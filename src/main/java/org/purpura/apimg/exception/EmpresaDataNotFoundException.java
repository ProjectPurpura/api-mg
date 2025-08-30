package org.purpura.apimg.exception;

public class EmpresaDataNotFoundException extends NotFoundException {
    public EmpresaDataNotFoundException(String tipo, String cnpj, String id) {
        super(String.format("Não foi possível encontrar %s da empresa '%s' com id %s", tipo, cnpj, id));
    }
}
