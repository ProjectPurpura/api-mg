package org.purpura.apimg.exception.empresa;

import org.purpura.apimg.exception.base.NotFoundException;

public class EmpresaNotFoundException extends NotFoundException {
    public EmpresaNotFoundException(String cnpj) {
        super(String.format("Empresa com CNPJ %s não encontrada", cnpj));
    }
}
