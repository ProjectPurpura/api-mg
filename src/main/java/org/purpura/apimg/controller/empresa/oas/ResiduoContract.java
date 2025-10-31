package org.purpura.apimg.controller.empresa.oas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.purpura.apimg.dto.schemas.empresa.residuo.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Validated
public interface ResiduoContract {
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
    List<ResiduoResponseDTO> getAllResiduosByCnpj(@Parameter(description = "CNPJ da empresa", example = "12345678000195") @PathVariable String cnpj);


    @Operation(summary = "Pegar todos os resíduos de todas as empresas.", description = "Retorna todos os resíduos de todas as empresas com exceção da própria empresa.",
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
    @GetMapping(value="/{cnpj}/residuo/viewmain")
    @ResponseStatus(HttpStatus.OK)
    List<ResiduoResponseDTO> getAllResiduosView(
            @Parameter(description = "CNPJ da empresa que desejas excluir da consulta", example = "12345678000195") @PathVariable String cnpj,
            @Parameter(description = "Limite de itens retornados", example = "50") @RequestParam Integer limit,
            @Parameter(description = "Página atual", example = "1") @RequestParam Integer page
    );


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

    @Operation(summary = "Aplicar múltiplas mudanças no estoque", description = "Faz uma baixa/aumento nos estoques dos resíduos da empresa em BATCH",
        responses = {
            @ApiResponse(responseCode = "200", description = "Baixas com sucesso",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ResiduoDownturnResponseDTO.class)
                )
            ),
            @ApiResponse(responseCode = "404", description = "Resíduo não encontrado"),
            @ApiResponse(responseCode = "400", description = "Não é possível abaixar o estoque, quantidade insuficiente")
        }
    )
    @PatchMapping(value = "/{cnpj}/residuo/downturn")
    @ResponseStatus(HttpStatus.OK)
    List<EstoqueDownturn> downturnResiduos(@Parameter(description = "CNPJ da empresa", example = "12345678000195") @PathVariable String cnpj,
                                           @RequestBody @Valid ResiduoDownturnRequestDTO residuoDownturnRequestDTO
    );


}
