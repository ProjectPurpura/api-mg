package org.purpura.apimg.controller.empresa.oas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.purpura.apimg.dto.schemas.empresa.base.EmpresaRequestDTO;
import org.purpura.apimg.dto.schemas.empresa.base.EmpresaResponseDTO;
import org.purpura.apimg.search.base.SearchKeywords;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseStatus;

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
    @ResponseStatus(HttpStatus.CREATED)
    void save(@RequestBody @Valid EmpresaRequestDTO empresaRequestDTO);

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
    @ResponseStatus(HttpStatus.OK)
    EmpresaResponseDTO get(@Parameter(description = "CNPJ da empresa", example = "12345678000195") @PathVariable String cnpj);


    @Operation(summary = "Excluir empresa", description = "Exclui uma empresa pelo CNPJ.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Empresa excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Empresa não encontrada")
        }
    )
    @DeleteMapping(value = "/{cnpj}")
    @ResponseStatus(HttpStatus.OK)
    void delete(@Parameter(description = "CNPJ da empresa", example = "12345678000195") @PathVariable String cnpj);

    @Operation(summary = "Atualizar empresa", description = "Atualiza os dados de uma empresa pelo CNPJ.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Empresa atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Empresa não encontrada")
        }
    )
    @PutMapping(value = "/{cnpj}")
    @ResponseStatus(HttpStatus.OK)
    void update(@Parameter(description = "CNPJ da empresa", example = "12345678000195") @PathVariable String cnpj,
                @RequestBody @Valid EmpresaRequestDTO empresaUpdateRequestDTO);

    @Operation(summary = "Buscar todas as empresas", description = "Retorna todas as empresas.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Empresas encontradas",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = EmpresaResponseDTO.class, type = "array")
                )
            ),
            @ApiResponse(responseCode = "404", description = "Empresas não encontradas")
        }
    )
    @GetMapping(value = "/all")
    @ResponseStatus(HttpStatus.OK)
    List<EmpresaResponseDTO> findAll();

    @Operation(summary = "Buscar empresas por texto", description = "Busca empresas por palavras-chave.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Empresas encontradas",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = EmpresaResponseDTO.class, type = "array")
                            )
                    )
            }
    )
    @GetMapping(value = "/search")
    @ResponseStatus(HttpStatus.OK)
    List<EmpresaResponseDTO> search(@Parameter(description = "Texto de busca", example = "reciclagem") @RequestParam @SearchKeywords @Valid String query);
}
