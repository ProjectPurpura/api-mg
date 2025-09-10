package org.purpura.apimg.model.empresa;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Unidade {
    KG("kilo"),
    T("tonelada"),
    L("litro"),
    ML("mililitro"),
    M("metro"),
    MC("metro cúbico");

    private final String nome;
}
