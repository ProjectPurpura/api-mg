package org.purpura.apimg.controller;

import jakarta.validation.Valid;
import org.purpura.apimg.dto.empresa.EmpresaRequestDTO;
import org.purpura.apimg.dto.empresa.EmpresaResponseDTO;
import org.purpura.apimg.service.EmpresaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Void> save(@RequestBody @Valid EmpresaRequestDTO empresaRequestDTO) {
        empresaService.insert(empresaRequestDTO);
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

    @PutMapping(value = "/update")
    public ResponseEntity<Void> update(@RequestBody @Valid EmpresaRequestDTO empresaRequestDTO) {
        empresaService.update(empresaRequestDTO);
        return ResponseEntity.ok().build();
    }
}
