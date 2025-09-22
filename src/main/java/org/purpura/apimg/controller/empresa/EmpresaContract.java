package org.purpura.apimg.controller.empresa;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.purpura.apimg.dto.empresa.base.EmpresaRequestDTO;
import org.purpura.apimg.dto.empresa.base.EmpresaResponseDTO;
import org.purpura.apimg.dto.empresa.pix.ChavePixRequestDTO;
import org.purpura.apimg.dto.empresa.residuo.ResiduoRequestDTO;
import org.purpura.apimg.dto.empresa.endereco.EnderecoRequestDTO;
import org.purpura.apimg.model.empresa.ChavePixModel;
import org.purpura.apimg.model.empresa.EmpresaModel;
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
    // region EMPRESA
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

    // region Endereco endpoints
    @Operation(summary = "Listar endereços da empresa", description = "Retorna todos os endereços de uma empresa.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Endereços retornados com sucesso")
    })
    @GetMapping(value = "/{cnpj}/endereco/all")
    ResponseEntity<List<EnderecoModel>> getEnderecos(@Parameter(description = "CNPJ da empresa", example = "12345678000195") @PathVariable String cnpj);

    @Operation(summary = "Buscar endereço por ID", description = "Retorna um endereço específico da empresa.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Endereço retornado com sucesso")
    })
    @GetMapping(value = "/{cnpj}/endereco/{id}")
    ResponseEntity<EnderecoModel> getEndereco(@Parameter(description = "CNPJ da empresa", example = "12345678000195") @PathVariable String cnpj,
                                              @Parameter(description = "ID do endereço", example = "1") @PathVariable String id);

    @Operation(summary = "Adicionar endereço à empresa", description = "Adiciona um novo endereço à empresa.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Endereço criado com sucesso")
    })
    @PostMapping(value = "/{cnpj}/endereco")
    ResponseEntity<Void> addEndereco(@Parameter(description = "CNPJ da empresa", example = "12345678000195") @PathVariable String cnpj,
                                    @RequestBody @Valid EnderecoRequestDTO endereco);

    @Operation(summary = "Atualizar endereço da empresa", description = "Atualiza um endereço da empresa.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso")
    })
    @PutMapping(value = "/{cnpj}/endereco/{id}")
    ResponseEntity<Void> updateEndereco(@Parameter(description = "CNPJ da empresa", example = "12345678000195") @PathVariable String cnpj,
                                       @Parameter(description = "ID do endereço", example = "1") @PathVariable String id,
                                       @RequestBody @Valid EnderecoRequestDTO endereco);

    @Operation(summary = "Excluir endereço da empresa", description = "Exclui um endereço da empresa.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Endereço excluído com sucesso")
    })
    @DeleteMapping(value = "/{cnpj}/endereco/{id}")
    ResponseEntity<Void> deleteEndereco(@Parameter(description = "CNPJ da empresa", example = "12345678000195") @PathVariable String cnpj,
                                       @Parameter(description = "ID do endereço", example = "1") @PathVariable String id);

    // region Chave Pix
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

    // region Resíduo
    @Operation(summary = "Listar resíduos da empresa", description = "Retorna todos os resíduos da empresa.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Resíduos retornados com sucesso")
    })
    @GetMapping(value = "/{cnpj}/residuo/all")
    ResponseEntity<List<ResiduoModel>> getResiduos(@Parameter(description = "CNPJ da empresa", example = "12345678000195") @PathVariable String cnpj);

    @Operation(summary = "Buscar resíduo por ID", description = "Retorna um resíduo específico da empresa.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Resíduo retornado com sucesso")
    })
    @GetMapping(value = "/{cnpj}/residuo/{id}")
    ResponseEntity<ResiduoModel> getResiduo(@Parameter(description = "CNPJ da empresa", example = "12345678000195") @PathVariable String cnpj,
                                            @Parameter(description = "ID do resíduo", example = "1") @PathVariable String id);

    @Operation(summary = "Adicionar resíduo à empresa", description = "Adiciona um novo resíduo à empresa.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Resíduo criado com sucesso")
    })
    @PostMapping(value = "/{cnpj}/residuo")
    ResponseEntity<Void> addResiduo(@Parameter(description = "CNPJ da empresa", example = "12345678000195") @PathVariable String cnpj,
                                   @RequestBody @Valid ResiduoRequestDTO residuoRequestDTO);

    @Operation(summary = "Atualizar resíduo da empresa", description = "Atualiza um resíduo da empresa.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Resíduo atualizado com sucesso")
    })
    @PutMapping(value = "/{cnpj}/residuo/{id}")
    ResponseEntity<Void> updateResiduo(@Parameter(description = "CNPJ da empresa", example = "12345678000195") @PathVariable String cnpj,
                                      @Parameter(description = "ID do resíduo", example = "1") @PathVariable String id,
                                      @RequestBody @Valid ResiduoRequestDTO residuoRequestDTO);

    @Operation(summary = "Excluir resíduo da empresa", description = "Exclui um resíduo da empresa.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Resíduo excluído com sucesso")
    })
    @DeleteMapping(value = "/{cnpj}/residuo/{id}")
    ResponseEntity<Void> deleteResiduo(@Parameter(description = "CNPJ da empresa", example = "12345678000195") @PathVariable String cnpj,
                                      @Parameter(description = "ID do resíduo", example = "1") @PathVariable String id);
    // endregion Resíduo
}

