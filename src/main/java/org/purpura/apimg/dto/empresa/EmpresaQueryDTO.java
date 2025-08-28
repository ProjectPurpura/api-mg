package org.purpura.apimg.dto.empresa;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaQueryDTO {
    @NotBlank(message = "A pesquisa deve conter pelo menos uma palavra chave")
    @NotNull(message = "A chave de pesquisa não deve ser nula")
    public String keywords;
}
