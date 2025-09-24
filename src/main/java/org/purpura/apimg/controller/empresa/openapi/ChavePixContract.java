package org.purpura.apimg.controller.empresa.openapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.purpura.apimg.dto.schemas.empresa.pix.ChavePixRequestDTO;
import org.purpura.apimg.model.empresa.ChavePixModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
public interface ChavePixContract {
    @Operation(summary = "Listar chaves Pix da empresa", description = "Retorna todas as chaves Pix da empresa.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Chaves Pix retornadas com sucesso")
    })
    @GetMapping(value = "/{cnpj}/pix/all")
    ResponseEntity<List<ChavePixModel>> getChaves(@Parameter(description = "CNPJ da empresa", example = "12345678000195") @PathVariable String cnpj);

    @Operation(summary = "Buscar chave Pix por ID", description = "Retorna uma chave Pix específica da empresa.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Chave Pix retornada com sucesso")
    })
    @GetMapping(value = "/{cnpj}/pix/{id}")
    ResponseEntity<ChavePixModel> getChave(@Parameter(description = "CNPJ da empresa", example = "12345678000195") @PathVariable String cnpj,
                                           @Parameter(description = "ID da chave Pix", example = "1") @PathVariable String id);

    @Operation(summary = "Adicionar chave Pix à empresa", description = "Adiciona uma nova chave Pix à empresa.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Chave Pix criada com sucesso")
    })
    @PostMapping(value = "/{cnpj}/pix")
    ResponseEntity<Void> addChave(@Parameter(description = "CNPJ da empresa", example = "12345678000195") @PathVariable String cnpj,
                                  @RequestBody @Valid ChavePixRequestDTO chavePixRequestDTO);

    @Operation(summary = "Atualizar chave Pix da empresa", description = "Atualiza uma chave Pix da empresa.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Chave Pix atualizada com sucesso")
    })
    @PutMapping(value = "/{cnpj}/pix/{id}")
    ResponseEntity<Void> updateResiduo(@Parameter(description = "CNPJ da empresa", example = "12345678000195") @PathVariable String cnpj,
                                       @Parameter(description = "ID da chave Pix", example = "1") @PathVariable String id,
                                       @RequestBody @Valid ChavePixRequestDTO chavePixRequestDTO);

    @Operation(summary = "Excluir chave Pix da empresa", description = "Exclui uma chave Pix da empresa.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Chave Pix excluída com sucesso")
    })
    @DeleteMapping(value = "/{cnpj}/pix/{id}")
    ResponseEntity<Void> deleteChave(@Parameter(description = "CNPJ da empresa", example = "12345678000195") @PathVariable String cnpj,
                                     @Parameter(description = "ID da chave Pix", example = "1") @PathVariable String id);
}
