package org.purpura.apimg.controller.empresa;

import jakarta.validation.Valid;
import org.purpura.apimg.controller.empresa.openapi.ChavePixContract;
import org.purpura.apimg.controller.empresa.openapi.EmpresaContract;
import org.purpura.apimg.controller.empresa.openapi.EnderecoContract;
import org.purpura.apimg.controller.empresa.openapi.ResiduoContract;
import org.purpura.apimg.dto.mapper.empresa.ChavePixMapper;
import org.purpura.apimg.dto.mapper.empresa.EmpresaMapper;
import org.purpura.apimg.dto.mapper.empresa.EnderecoMapper;
import org.purpura.apimg.dto.mapper.empresa.ResiduoMapper;
import org.purpura.apimg.dto.schemas.empresa.base.EmpresaRequestDTO;
import org.purpura.apimg.dto.schemas.empresa.base.EmpresaResponseDTO;
import org.purpura.apimg.dto.schemas.empresa.endereco.EnderecoResponseDTO;
import org.purpura.apimg.dto.schemas.empresa.pix.ChavePixRequestDTO;
import org.purpura.apimg.dto.schemas.empresa.residuo.ResiduoRequestDTO;
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

import org.purpura.apimg.dto.schemas.empresa.endereco.EnderecoRequestDTO;
import org.purpura.apimg.model.empresa.EnderecoModel;

@RestController
@RequestMapping("/empresa")
@Validated
public class EmpresaController implements EmpresaContract, EnderecoContract, ResiduoContract, ChavePixContract {

    private final EmpresaService empresaService;
    private final EmpresaMapper empresaMapper;
    private final EnderecoMapper enderecoMapper;
    private final ChavePixMapper chavePixMapper;
    private final ResiduoMapper residuoMapper;


    public EmpresaController(
            EmpresaService empresaService,
            EmpresaMapper empresaMapper,
            EnderecoMapper enderecoMapper,
            ChavePixMapper chavePixMapper,
            ResiduoMapper residuoMapper
    ) {
        this.empresaService = empresaService;
        this.empresaMapper = empresaMapper;
        this.enderecoMapper = enderecoMapper;
        this.chavePixMapper = chavePixMapper;
        this.residuoMapper = residuoMapper;
    }

    // region EMPRESA
    @Override
    public ResponseEntity<Void> save(@RequestBody @Valid EmpresaRequestDTO empresaRequestDTO) {
        empresaService.insert(empresaRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<EmpresaResponseDTO> get(@PathVariable String cnpj) {
        return ResponseEntity.ok(empresaMapper.toResponse(empresaService.findByCnpj(cnpj)));
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable String cnpj) {
        empresaService.deleteByCnpj(cnpj);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> update(@PathVariable String cnpj,
                                       @RequestBody @Valid EmpresaRequestDTO empresaUpdateRequestDTO)
    {
        empresaService.update(cnpj, empresaUpdateRequestDTO);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<EmpresaResponseDTO>> findAll() {
        return ResponseEntity.ok(empresaMapper.toResponseList(empresaService.findAll()));
    }

    @Override
    public ResponseEntity<List<EmpresaResponseDTO>> search(@RequestParam @SearchKeywords @Valid String query) {
        List<EmpresaModel> found = empresaService.search(query);
        return ResponseEntity.ok(empresaMapper.toResponseList(found));
    }

    // endregion EMPRESA
    // region Endereco endpoints
    @Override
    public ResponseEntity<List<EnderecoResponseDTO>> getEnderecos(@PathVariable String cnpj) {
        return ResponseEntity.ok(enderecoMapper.toResponseList(empresaService.findEnderecosByCnpj(cnpj)));
    }

    @Override
    public ResponseEntity<EnderecoResponseDTO> getEndereco(@PathVariable String cnpj, @PathVariable String id) {
        return ResponseEntity.ok(enderecoMapper.toResponse(empresaService.findEnderecoById(cnpj, id)));
    }

    @Override
    public ResponseEntity<EnderecoResponseDTO> addEndereco(@PathVariable String cnpj,
                                            @RequestBody @Valid EnderecoRequestDTO endereco) {
        EnderecoModel enderecoModel = empresaService.addEndereco(cnpj, endereco);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(enderecoMapper.toResponse(enderecoModel));
    }

    @Override
    public ResponseEntity<Void> updateEndereco(@PathVariable String cnpj,
                                               @PathVariable String id,
                                               @RequestBody @Valid EnderecoRequestDTO endereco) {
        empresaService.updateEndereco(cnpj, id, endereco);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deleteEndereco(@PathVariable String cnpj,
                                               @PathVariable String id) {
        empresaService.deleteEndereco(cnpj, id);
        return ResponseEntity.ok().build();
    }

    // endregion Endereco endpoints

    // region Chave Pix
    @Override
    public ResponseEntity<List<ChavePixModel>> getChaves(@PathVariable String cnpj) {
        return ResponseEntity.ok(empresaService.findChavesPixByCnpj(cnpj));
    }

    @Override
    public ResponseEntity<ChavePixModel> getChave(@PathVariable String cnpj, @PathVariable String id) {
        return ResponseEntity.ok(empresaService.findChavePixById(cnpj, id));
    }

    @Override
    public ResponseEntity<Void> addChave(@PathVariable String cnpj,
                                         @RequestBody @Valid ChavePixRequestDTO chavePixRequestDTO) {
        empresaService.addChavePix(cnpj, chavePixRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<Void> updateResiduo(@PathVariable String cnpj,
                                              @PathVariable String id,
                                              @RequestBody @Valid ChavePixRequestDTO chavePixRequestDTO) {
        empresaService.updateChavePix(cnpj, id, chavePixRequestDTO);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deleteChave(@PathVariable String cnpj,
                                            @PathVariable String id) {
        empresaService.deleteChavePix(cnpj, id);
        return ResponseEntity.ok().build();
    }

    // endregion Chave Pix

    // region Resíduo
    @Override
    public ResponseEntity<List<ResiduoModel>> getResiduos(@PathVariable String cnpj) {
        return ResponseEntity.ok(empresaService.findResiduosByCnpj(cnpj));
    }

    @Override
    public ResponseEntity<ResiduoModel> getResiduo(@PathVariable String cnpj, @PathVariable String id) {
        return ResponseEntity.ok(empresaService.findResiduoById(cnpj, id));
    }

    @Override
    public ResponseEntity<Void> addResiduo(@PathVariable String cnpj,
                                           @RequestBody @Valid ResiduoRequestDTO residuoRequestDTO) {
        empresaService.addResiduo(cnpj, residuoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<Void> updateResiduo(@PathVariable String cnpj,
                                              @PathVariable String id,
                                              @RequestBody @Valid ResiduoRequestDTO residuoRequestDTO) {
        empresaService.updateResiduo(cnpj, id, residuoRequestDTO);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deleteResiduo(@PathVariable String cnpj,
                                              @PathVariable String id) {
        empresaService.deleteResiduo(cnpj, id);
        return ResponseEntity.ok().build();
    }
    // endregion Resíduo
}
