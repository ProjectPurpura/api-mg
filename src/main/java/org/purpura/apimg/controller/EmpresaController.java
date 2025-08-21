package org.purpura.apimg.controller;

import jakarta.validation.Valid;
import org.purpura.apimg.dto.empresa.EmpresaRequestDTO;
import org.purpura.apimg.model.empresa.EmpresaModel;
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
    public ResponseEntity<Void> saveEmpresa(@RequestBody @Valid EmpresaRequestDTO empresaRequestDTO) {
        empresaService.save(empresaRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EmpresaModel> getEmpresa(@PathVariable String id) {
        EmpresaModel empresaModel = empresaService.findById(id);
        return ResponseEntity.ok(empresaModel);
    }
}
