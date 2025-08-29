package org.purpura.apimg.controller;

import jakarta.validation.Valid;
import org.purpura.apimg.dto.empresa.EmpresaSaveRequestDTO;
import org.purpura.apimg.dto.empresa.EmpresaResponseDTO;
import org.purpura.apimg.dto.empresa.EmpresaUpdateRequestDTO;
import org.purpura.apimg.model.empresa.EmpresaModel;
import org.purpura.apimg.search.base.SearchKeywords;
import org.purpura.apimg.service.EmpresaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.purpura.apimg.dto.endereco.EnderecoRequestDTO;
import org.purpura.apimg.model.empresa.EnderecoModel;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Void> save(@RequestBody @Valid EmpresaSaveRequestDTO empresaSaveRequestDTO) {
        empresaService.insert(empresaSaveRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "/get/{cnpj}")
    public ResponseEntity<EmpresaResponseDTO> get(@PathVariable String cnpj) {
        return ResponseEntity.ok(new EmpresaResponseDTO(empresaService.findByCnpj(cnpj)));
    }

    @DeleteMapping(value = "/delete/{cnpj}")
    public ResponseEntity<Void> delete(@PathVariable String cnpj) {
        empresaService.deleteByCnpj(cnpj);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/update/{cnpj}")
    public ResponseEntity<Void> update(@PathVariable String cnpj,
                                       @RequestBody @Valid EmpresaUpdateRequestDTO empresaUpdateRequestDTO)
    {
        empresaService.update(cnpj, empresaUpdateRequestDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<EmpresaResponseDTO>> findAll() {
        return ResponseEntity.ok(empresaService.findAll().stream()
                .map(EmpresaResponseDTO::new)
                .toList());
    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<EmpresaResponseDTO>> search(@RequestParam @SearchKeywords @Valid String query) {
        List<EmpresaModel> found = empresaService.search(query);
        return ResponseEntity.ok(found.stream()
                .map(EmpresaResponseDTO::new)
                .toList());
    }

    // region Endereco endpoints
    @GetMapping(value = "/{cnpj}/endereco")
    public ResponseEntity<List<EnderecoModel>> getEnderecos(@PathVariable String cnpj) {
        return ResponseEntity.ok(empresaService.findEnderecosByCnpj(cnpj));
    }

    @PostMapping(value = "/{cnpj}/endereco")
    public ResponseEntity<Void> addEndereco(@PathVariable String cnpj,
                                           @RequestBody @Valid EnderecoRequestDTO endereco) {
        empresaService.addEndereco(cnpj, endereco);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(value = "/{cnpj}/endereco/{id}")
    public ResponseEntity<Void> updateEndereco(@PathVariable String cnpj,
                                              @PathVariable String id,
                                              @RequestBody @Valid EnderecoRequestDTO endereco) {
        empresaService.updateEndereco(cnpj, id, endereco);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{cnpj}/endereco/{id}")
    public ResponseEntity<Void> deleteEndereco(@PathVariable String cnpj,
                                              @PathVariable String id) {
        empresaService.deleteEndereco(cnpj, id);
        return ResponseEntity.ok().build();
    }
    // endregion Endereco endpoints

    // region Chave Pix

    // endregion Chave Pix
}
