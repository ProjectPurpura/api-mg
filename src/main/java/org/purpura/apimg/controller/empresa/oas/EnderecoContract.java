package org.purpura.apimg.controller.empresa.oas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.purpura.apimg.dto.schemas.empresa.endereco.EnderecoRequestDTO;
import org.purpura.apimg.dto.schemas.empresa.endereco.EnderecoResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;


public interface EnderecoContract {
    @Operation(summary = "Listar endereços da empresa", description = "Retorna todos os endereços de uma empresa.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Endereços retornados com sucesso",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = EnderecoResponseDTO.class)
                )
            )
        }
    )
    @GetMapping(value = "/{cnpj}/endereco/all")
    ResponseEntity<List<EnderecoResponseDTO>> getEnderecos(@Parameter(description = "CNPJ da empresa", example = "12345678000195") @PathVariable String cnpj);

    @Operation(summary = "Buscar endereço por ID", description = "Retorna um endereço específico da empresa.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Endereço retornado com sucesso",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = EnderecoResponseDTO.class)
                )
            )
        }
    )
    @GetMapping(value = "/{cnpj}/endereco/{id}")
    ResponseEntity<EnderecoResponseDTO> getEndereco(@Parameter(description = "CNPJ da empresa", example = "12345678000195") @PathVariable String cnpj,
                                              @Parameter(description = "ID do endereço", example = "1") @PathVariable String id);

    @Operation(summary = "Adicionar endereço à empresa", description = "Adiciona um novo endereço à empresa.",
        responses = {
            @ApiResponse(responseCode = "201", description = "Endereço criado com sucesso",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = EnderecoResponseDTO.class)
                )
            )
        }
    )
    @PostMapping(value = "/{cnpj}/endereco")
    ResponseEntity<EnderecoResponseDTO> addEndereco(@Parameter(description = "CNPJ da empresa", example = "12345678000195") @PathVariable String cnpj,
                                                    @RequestBody @Valid EnderecoRequestDTO endereco);

    @Operation(summary = "Atualizar endereço da empresa", description = "Atualiza um endereço da empresa.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso")
        }
    )
    @PutMapping(value = "/{cnpj}/endereco/{id}")
    ResponseEntity<Void> updateEndereco(@Parameter(description = "CNPJ da empresa", example = "12345678000195") @PathVariable String cnpj,
                                        @Parameter(description = "ID do endereço", example = "1") @PathVariable String id,
                                        @RequestBody @Valid EnderecoRequestDTO endereco);

    @Operation(summary = "Excluir endereço da empresa", description = "Exclui um endereço da empresa.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Endereço excluído com sucesso")
        }
    )
    @DeleteMapping(value = "/{cnpj}/endereco/{id}")
    ResponseEntity<Void> deleteEndereco(@Parameter(description = "CNPJ da empresa", example = "12345678000195") @PathVariable String cnpj,
                                        @Parameter(description = "ID do endereço", example = "1") @PathVariable String id);

}
