package org.purpura.apimg.controller;

import jakarta.validation.Valid;
import org.purpura.apimg.dto.empresa.base.EmpresaSaveRequestDTO;
import org.purpura.apimg.dto.empresa.base.EmpresaResponseDTO;
import org.purpura.apimg.dto.empresa.base.EmpresaUpdateRequestDTO;
import org.purpura.apimg.dto.empresa.pix.ChavePixRequestDTO;
import org.purpura.apimg.dto.empresa.residuo.ResiduoRequestDTO;
import org.purpura.apimg.model.empresa.ChavePixModel;
import org.purpura.apimg.model.empresa.EmpresaModel;
import org.purpura.apimg.model.empresa.ResiduoModel;
import org.purpura.apimg.search.base.SearchKeywords;
import org.purpura.apimg.service.EmpresaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.purpura.apimg.dto.empresa.endereco.EnderecoRequestDTO;
import org.purpura.apimg.model.empresa.EnderecoModel;

@RestController
@RequestMapping("/empresa")
@Validated
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    // region EMPRESA
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid EmpresaSaveRequestDTO empresaSaveRequestDTO) {
        empresaService.insert(empresaSaveRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "/{cnpj}")
    public ResponseEntity<EmpresaResponseDTO> get(@PathVariable String cnpj) {
        return ResponseEntity.ok(new EmpresaResponseDTO(empresaService.findByCnpj(cnpj)));
    }

    @DeleteMapping(value = "/{cnpj}")
    public ResponseEntity<Void> delete(@PathVariable String cnpj) {
        empresaService.deleteByCnpj(cnpj);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/{cnpj}")
    public ResponseEntity<Void> update(@PathVariable String cnpj,
                                       @RequestBody @Valid EmpresaUpdateRequestDTO empresaUpdateRequestDTO)
    {
        empresaService.update(cnpj, empresaUpdateRequestDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<EmpresaResponseDTO>> findAll() {
        return ResponseEntity.ok(mapToDTOs(empresaService.findAll()));
    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<EmpresaResponseDTO>> search(@RequestParam @SearchKeywords @Valid String query) {
        List<EmpresaModel> found = empresaService.search(query);
        return ResponseEntity.ok(mapToDTOs(found));
    }

    private static List<EmpresaResponseDTO> mapToDTOs(List<EmpresaModel> models) {
        return models.stream().map(EmpresaResponseDTO::new).toList();
    }
    // endregion EMPRESA
    // region Endereco endpoints
    @GetMapping(value = "/{cnpj}/endereco/all")
    public ResponseEntity<List<EnderecoModel>> getEnderecos(@PathVariable String cnpj) {
        return ResponseEntity.ok(empresaService.findEnderecosByCnpj(cnpj));
    }

    @GetMapping("/{cnpj}/endereco/{id}")
    public ResponseEntity<EnderecoModel> getEndereco(@PathVariable String cnpj, @PathVariable String id) {
        return ResponseEntity.ok(empresaService.findEnderecoById(cnpj, id));
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
    @GetMapping(value = "/{cnpj}/pix/all")
    public ResponseEntity<List<ChavePixModel>> getChaves(@PathVariable String cnpj) {
        return ResponseEntity.ok(empresaService.findChavesPixByCnpj(cnpj));
    }

    @GetMapping("/{cnpj}/pix/{id}")
    public ResponseEntity<ChavePixModel> getChave(@PathVariable String cnpj, @PathVariable String id) {
        return ResponseEntity.ok(empresaService.findChavePixById(cnpj, id));
    }

    @PostMapping(value = "/{cnpj}/pix")
    public ResponseEntity<Void> addChave(@PathVariable String cnpj,
                                         @RequestBody @Valid ChavePixRequestDTO chavePixRequestDTO) {
        empresaService.addChavePix(cnpj, chavePixRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(value = "/{cnpj}/pix/{id}")
    public ResponseEntity<Void> updateResiduo(@PathVariable String cnpj,
                                              @PathVariable String id,
                                              @RequestBody @Valid ChavePixRequestDTO chavePixRequestDTO) {
        empresaService.updateChavePix(cnpj, id, chavePixRequestDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{cnpj}/pix/{id}")
    public ResponseEntity<Void> deleteChave(@PathVariable String cnpj,
                                            @PathVariable String id) {
        empresaService.deleteChavePix(cnpj, id);
        return ResponseEntity.ok().build();
    }

    // endregion Chave Pix

    // region Resíduo
    @GetMapping(value = "/{cnpj}/residuo/all")
    public ResponseEntity<List<ResiduoModel>> getResiduos(@PathVariable String cnpj) {
        return ResponseEntity.ok(empresaService.findResiduosByCnpj(cnpj));
    }

    @GetMapping("/{cnpj}/residuo/{id}")
    public ResponseEntity<ResiduoModel> getResiduo(@PathVariable String cnpj, @PathVariable String id) {
        return ResponseEntity.ok(empresaService.findResiduoById(cnpj, id));
    }

    @PostMapping(value = "/{cnpj}/residuo")
    public ResponseEntity<Void> addResiduo(@PathVariable String cnpj,
                                         @RequestBody @Valid ResiduoRequestDTO residuoRequestDTO) {
        empresaService.addResiduo(cnpj, residuoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(value = "/{cnpj}/residuo/{id}")
    public ResponseEntity<Void> updateResiduo(@PathVariable String cnpj,
                                              @PathVariable String id,
                                              @RequestBody @Valid ResiduoRequestDTO residuoRequestDTO) {
        empresaService.updateResiduo(cnpj, id, residuoRequestDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{cnpj}/residuo/{id}")
    public ResponseEntity<Void> deleteResiduo(@PathVariable String cnpj,
                                            @PathVariable String id) {
        empresaService.deleteResiduo(cnpj, id);
        return ResponseEntity.ok().build();
    }
    // endregion Resíduo
}
