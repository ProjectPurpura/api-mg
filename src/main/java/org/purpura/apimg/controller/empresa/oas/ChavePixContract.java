package org.purpura.apimg.controller.empresa.oas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.purpura.apimg.dto.schemas.empresa.pix.ChavePixRequestDTO;
import org.purpura.apimg.dto.schemas.empresa.pix.ChavePixResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
public interface ChavePixContract {
    @Operation(summary = "Listar chaves Pix da empresa", description = "Retorna todas as chaves Pix da empresa.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Chaves Pix retornadas com sucesso",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ChavePixResponseDTO.class)
                )
            ),
            @ApiResponse(responseCode = "404", description = "Empresa não encontrada")
        }
    )
    @GetMapping(value = "/{cnpj}/pix/all")
    ResponseEntity<List<ChavePixResponseDTO>> getChaves(@Parameter(description = "CNPJ da empresa", example = "12345678000195") @PathVariable String cnpj);

    @Operation(summary = "Buscar chave Pix por ID", description = "Retorna uma chave Pix específica da empresa.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Chave Pix retornada com sucesso",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ChavePixResponseDTO.class)
                )
            ),
            @ApiResponse(responseCode = "404", description = "Empresa / Chave Pix não encontrada")
        }
    )
    @GetMapping(value = "/{cnpj}/pix/{id}")
    ResponseEntity<ChavePixResponseDTO> getChave(@Parameter(description = "CNPJ da empresa", example = "12345678000195") @PathVariable String cnpj,
                                                 @Parameter(description = "ID da chave Pix", example = "1") @PathVariable String id);

    @Operation(summary = "Adicionar chave Pix à empresa", description = "Adiciona uma nova chave Pix à empresa.",
        responses = {
            @ApiResponse(responseCode = "201", description = "Chave Pix criada com sucesso",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ChavePixResponseDTO.class)
                )
            )
        }
    )
    @PostMapping(value = "/{cnpj}/pix")
    ResponseEntity<ChavePixResponseDTO> addChave(@Parameter(description = "CNPJ da empresa", example = "12345678000195") @PathVariable String cnpj,
                                  @RequestBody @Valid ChavePixRequestDTO chavePixRequestDTO);

    @Operation(summary = "Atualizar chave Pix da empresa", description = "Atualiza uma chave Pix da empresa.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Chave Pix atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Chave Pix não encontrada")
        }
    )
    @PutMapping(value = "/{cnpj}/pix/{id}")
    ResponseEntity<Void> updateChavePix(@Parameter(description = "CNPJ da empresa", example = "12345678000195") @PathVariable String cnpj,
                                        @Parameter(description = "ID da chave Pix", example = "1") @PathVariable String id,
                                        @RequestBody @Valid ChavePixRequestDTO chavePixRequestDTO);

    @Operation(summary = "Excluir chave Pix da empresa", description = "Exclui uma chave Pix da empresa.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Chave Pix excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Empresa / Chave Pix não encontrada")
        }
    )
    @DeleteMapping(value = "/{cnpj}/pix/{id}")
    ResponseEntity<Void> deleteChave(@Parameter(description = "CNPJ da empresa", example = "12345678000195") @PathVariable String cnpj,
                                     @Parameter(description = "ID da chave Pix", example = "1") @PathVariable String id);
}
