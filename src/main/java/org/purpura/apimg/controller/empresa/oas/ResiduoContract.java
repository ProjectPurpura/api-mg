package org.purpura.apimg.controller.empresa.oas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.purpura.apimg.dto.schemas.empresa.residuo.ResiduoRequestDTO;
import org.purpura.apimg.dto.schemas.empresa.residuo.ResiduoResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Validated
public interface ResiduoContract {
    @Operation(summary = "Listar resíduos da empresa", description = "Retorna todos os resíduos da empresa.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Resíduos retornados com sucesso",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ResiduoResponseDTO.class, type = "array")
                )
            ),
            @ApiResponse(responseCode = "404", description = "Empresa não encontrada")
        }
    )
    @GetMapping(value = "/{cnpj}/residuo/all")
    @ResponseStatus(HttpStatus.OK)
    List<ResiduoResponseDTO> getResiduos(@Parameter(description = "CNPJ da empresa", example = "12345678000195") @PathVariable String cnpj);

    @Operation(summary = "Buscar resíduo por ID", description = "Retorna um resíduo específico da empresa.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Resíduo retornado com sucesso",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ResiduoResponseDTO.class)
                )
            ),
            @ApiResponse(responseCode = "404", description = "Empresa / Resíduo não encontrado")
        }
    )
    @GetMapping(value = "/{cnpj}/residuo/{id}")
    @ResponseStatus(HttpStatus.OK)
    ResiduoResponseDTO getResiduo(@Parameter(description = "CNPJ da empresa", example = "12345678000195") @PathVariable String cnpj,
                                  @Parameter(description = "ID do resíduo", example = "1") @PathVariable String id);

    @Operation(summary = "Adicionar resíduo à empresa", description = "Adiciona um novo resíduo à empresa.",
        responses = {
            @ApiResponse(responseCode = "201", description = "Resíduo criado com sucesso",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ResiduoResponseDTO.class)
                )
            ),
            @ApiResponse(responseCode = "404", description = "Empresa não encontrada")
        }
    )
    @PostMapping(value = "/{cnpj}/residuo")
    @ResponseStatus(HttpStatus.CREATED)
    ResiduoResponseDTO addResiduo(@Parameter(description = "CNPJ da empresa", example = "12345678000195") @PathVariable String cnpj,
                                  @RequestBody @Valid ResiduoRequestDTO residuoRequestDTO);

    @Operation(summary = "Atualizar resíduo da empresa", description = "Atualiza um resíduo da empresa.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Resíduo atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Resíduo não encontrado")
        }
    )
    @PutMapping(value = "/{cnpj}/residuo/{id}")
    @ResponseStatus(HttpStatus.OK)
    void updateResiduo(@Parameter(description = "CNPJ da empresa", example = "12345678000195") @PathVariable String cnpj,
                       @Parameter(description = "ID do resíduo", example = "1") @PathVariable String id,
                       @RequestBody @Valid ResiduoRequestDTO residuoRequestDTO);

    @Operation(summary = "Excluir resíduo da empresa", description = "Exclui um resíduo da empresa.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Resíduo excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Resíduo não encontrado")
        }
    )
    @DeleteMapping(value = "/{cnpj}/residuo/{id}")
    @ResponseStatus(HttpStatus.OK)
    void deleteResiduo(@Parameter(description = "CNPJ da empresa", example = "12345678000195") @PathVariable String cnpj,
                       @Parameter(description = "ID do resíduo", example = "1") @PathVariable String id);
}
