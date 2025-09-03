package org.purpura.apimg.model.empresa;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Unidade {
    KILO("KG"),
    TONELADA("T"),
    LITRO("L"),
    MILILITRO("mL"),
    METRO("m"),
    METRO_CUBICO("m³");

    private final String sigla;
}
