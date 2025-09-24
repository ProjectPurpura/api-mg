package org.purpura.apimg.controller.empresa.openapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.purpura.apimg.dto.schemas.empresa.base.EmpresaRequestDTO;
import org.purpura.apimg.dto.schemas.empresa.base.EmpresaResponseDTO;
import org.purpura.apimg.search.base.SearchKeywords;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Validated
public interface EmpresaContract {
    @Operation(summary = "Criar empresa", description = "Cria uma nova empresa.",
        responses = {
            @ApiResponse(responseCode = "201", description = "Empresa criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
        }
    )
    @PostMapping
    ResponseEntity<Void> save(@RequestBody @Valid EmpresaRequestDTO empresaRequestDTO);

    @Operation(summary = "Buscar empresa por CNPJ", description = "Retorna os dados de uma empresa pelo CNPJ.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Empresa encontrada",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = EmpresaResponseDTO.class)
                )
            ),
            @ApiResponse(responseCode = "404", description = "Empresa não encontrada")
        }
    )
    @GetMapping(value = "/{cnpj}")
    ResponseEntity<EmpresaResponseDTO> get(@Parameter(description = "CNPJ da empresa", example = "12345678000195") @PathVariable String cnpj);

    @Operation(summary = "Excluir empresa", description = "Exclui uma empresa pelo CNPJ.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Empresa excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Empresa não encontrada")
        }
    )
    @DeleteMapping(value = "/{cnpj}")
    ResponseEntity<Void> delete(@Parameter(description = "CNPJ da empresa", example = "12345678000195") @PathVariable String cnpj);

    @Operation(summary = "Atualizar empresa", description = "Atualiza os dados de uma empresa pelo CNPJ.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Empresa atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Empresa não encontrada")
        }
    )
    @PutMapping(value = "/{cnpj}")
    ResponseEntity<Void> update(@Parameter(description = "CNPJ da empresa", example = "12345678000195") @PathVariable String cnpj,
                               @RequestBody @Valid EmpresaRequestDTO empresaUpdateRequestDTO);

    @Operation(summary = "Listar todas empresas", description = "Retorna todas as empresas cadastradas.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Lista de empresas retornada com sucesso",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = EmpresaResponseDTO.class)
                )
            )
        }
    )
    @GetMapping(value = "/all")
    ResponseEntity<List<EmpresaResponseDTO>> findAll();

    @Operation(summary = "Buscar empresas por texto", description = "Busca empresas por palavras-chave.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Empresas encontradas",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = EmpresaResponseDTO.class)
                )
            )
        }
    )
    @GetMapping(value = "/search")
    ResponseEntity<List<EmpresaResponseDTO>> search(@Parameter(description = "Texto de busca", example = "reciclagem") @RequestParam @SearchKeywords @Valid String query);
}
