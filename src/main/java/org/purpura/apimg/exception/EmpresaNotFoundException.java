package org.purpura.apimg.exception;

public class EmpresaNotFoundException extends NotFoundException {
    public EmpresaNotFoundException(String cnpj) {
        super(String.format("Empresa com CNPJ %s não encontrada", cnpj));
    }
}
