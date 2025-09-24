package org.purpura.apimg.controller.empresa.openapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.purpura.apimg.dto.schemas.empresa.base.EmpresaRequestDTO;
import org.purpura.apimg.dto.schemas.empresa.base.EmpresaResponseDTO;
import org.purpura.apimg.dto.schemas.empresa.pix.ChavePixRequestDTO;
import org.purpura.apimg.dto.schemas.empresa.residuo.ResiduoRequestDTO;
import org.purpura.apimg.dto.schemas.empresa.endereco.EnderecoRequestDTO;
import org.purpura.apimg.model.empresa.ChavePixModel;
import org.purpura.apimg.model.empresa.ResiduoModel;
import org.purpura.apimg.model.empresa.EnderecoModel;
import org.purpura.apimg.search.base.SearchKeywords;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Validated
public interface EmpresaContract {
    @Operation(summary = "Criar empresa", description = "Cria uma nova empresa.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Empresa criada com sucesso")
    })
    @PostMapping
    ResponseEntity<Void> save(@RequestBody @Valid EmpresaRequestDTO empresaRequestDTO);

    @Operation(summary = "Buscar empresa por CNPJ", description = "Retorna os dados de uma empresa pelo CNPJ.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Empresa encontrada")
    })
    @GetMapping(value = "/{cnpj}")
    ResponseEntity<EmpresaResponseDTO> get(@Parameter(description = "CNPJ da empresa", example = "12345678000195") @PathVariable String cnpj);

    @Operation(summary = "Excluir empresa", description = "Exclui uma empresa pelo CNPJ.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Empresa excluída com sucesso")
    })
    @DeleteMapping(value = "/{cnpj}")
    ResponseEntity<Void> delete(@Parameter(description = "CNPJ da empresa", example = "12345678000195") @PathVariable String cnpj);

    @Operation(summary = "Atualizar empresa", description = "Atualiza os dados de uma empresa pelo CNPJ.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Empresa atualizada com sucesso")
    })
    @PutMapping(value = "/{cnpj}")
    ResponseEntity<Void> update(@Parameter(description = "CNPJ da empresa", example = "12345678000195") @PathVariable String cnpj,
                               @RequestBody @Valid EmpresaRequestDTO empresaUpdateRequestDTO);

    @Operation(summary = "Listar todas empresas", description = "Retorna todas as empresas cadastradas.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Lista de empresas retornada com sucesso")
    })
    @GetMapping(value = "/all")
    ResponseEntity<List<EmpresaResponseDTO>> findAll();

    @Operation(summary = "Buscar empresas por texto", description = "Busca empresas por palavras-chave.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Empresas encontradas")
    })
    @GetMapping(value = "/search")
    ResponseEntity<List<EmpresaResponseDTO>> search(@Parameter(description = "Texto de busca", example = "reciclagem") @RequestParam @SearchKeywords @Valid String query);
}

