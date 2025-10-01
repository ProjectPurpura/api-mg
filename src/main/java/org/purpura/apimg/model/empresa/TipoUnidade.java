package org.purpura.apimg.model.empresa;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoUnidade {
    KG("kilo"),
    T("tonelada"),
    L("litro"),
    ML("mililitro"),
    M("metro"),
    MC("metro cúbico"),
    G("grama");

    private final String nome;
}
