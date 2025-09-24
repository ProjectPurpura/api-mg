package org.purpura.apimg.controller.empresa;

import jakarta.validation.Valid;
import org.purpura.apimg.controller.empresa.oas.ChavePixContract;
import org.purpura.apimg.controller.empresa.oas.EmpresaContract;
import org.purpura.apimg.controller.empresa.oas.EnderecoContract;
import org.purpura.apimg.controller.empresa.oas.ResiduoContract;
import org.purpura.apimg.dto.mapper.empresa.ChavePixMapper;
import org.purpura.apimg.dto.mapper.empresa.EmpresaMapper;
import org.purpura.apimg.dto.mapper.empresa.EnderecoMapper;
import org.purpura.apimg.dto.mapper.empresa.ResiduoMapper;
import org.purpura.apimg.dto.schemas.empresa.base.EmpresaRequestDTO;
import org.purpura.apimg.dto.schemas.empresa.base.EmpresaResponseDTO;
import org.purpura.apimg.dto.schemas.empresa.endereco.EnderecoResponseDTO;
import org.purpura.apimg.dto.schemas.empresa.pix.ChavePixRequestDTO;
import org.purpura.apimg.dto.schemas.empresa.pix.ChavePixResponseDTO;
import org.purpura.apimg.dto.schemas.empresa.residuo.ResiduoRequestDTO;
import org.purpura.apimg.dto.schemas.empresa.residuo.ResiduoResponseDTO;
import org.purpura.apimg.search.base.SearchKeywords;
import org.purpura.apimg.service.EmpresaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.purpura.apimg.dto.schemas.empresa.endereco.EnderecoRequestDTO;
import org.purpura.apimg.model.empresa.EnderecoModel;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;

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
    @CacheEvict(value = {"empresas", "empresa"}, allEntries = true)
    public ResponseEntity<Void> save(@RequestBody @Valid EmpresaRequestDTO empresaRequestDTO) {
        empresaService.insert(empresaRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    @Cacheable(value = "empresa", key = "#cnpj")
    public ResponseEntity<EmpresaResponseDTO> get(@PathVariable String cnpj) {
        return ResponseEntity.ok(empresaMapper.toResponse(empresaService.findByCnpj(cnpj)));
    }

    @Override
    @CacheEvict(value = {"empresas", "empresa"}, allEntries = true)
    public ResponseEntity<Void> delete(@PathVariable String cnpj) {
        empresaService.deleteByCnpj(cnpj);
        return ResponseEntity.ok().build();
    }

    @Override
    @CacheEvict(value = {"empresas", "empresa"}, allEntries = true)
    public ResponseEntity<Void> update(@PathVariable String cnpj,
                                       @RequestBody @Valid EmpresaRequestDTO empresaUpdateRequestDTO)
    {
        empresaService.update(cnpj, empresaUpdateRequestDTO);
        return ResponseEntity.ok().build();
    }

    @Override
    @Cacheable(value = "empresas")
    public ResponseEntity<List<EmpresaResponseDTO>> findAll() {
        return ResponseEntity.ok(empresaMapper.toResponseList(empresaService.findAll()));
    }

    @Override
    @Cacheable(value = "empresas", key = "#query")
    public ResponseEntity<List<EmpresaResponseDTO>> search(@RequestParam @SearchKeywords @Valid String query) {
        return ResponseEntity.ok(empresaMapper.toResponseList(empresaService.search(query)));
    }
    // endregion EMPRESA
    // region Endereco endpoints
    @Override
    @Cacheable(value = "enderecos", key = "#cnpj")
    public ResponseEntity<List<EnderecoResponseDTO>> getEnderecos(@PathVariable String cnpj) {
        return ResponseEntity.ok(enderecoMapper.toResponseList(empresaService.findEnderecosByCnpj(cnpj)));
    }

    @Override
    @Cacheable(value = "endereco", key = "#cnpj + ':' + #id")
    public ResponseEntity<EnderecoResponseDTO> getEndereco(@PathVariable String cnpj, @PathVariable String id) {
        return ResponseEntity.ok(enderecoMapper.toResponse(empresaService.findEnderecoById(cnpj, id)));
    }

    @Override
    @CacheEvict(value = {"enderecos", "endereco"}, key = "#cnpj", allEntries = true)
    public ResponseEntity<EnderecoResponseDTO> addEndereco(@PathVariable String cnpj,
                                            @RequestBody @Valid EnderecoRequestDTO endereco) {
        EnderecoModel enderecoModel = empresaService.addEndereco(cnpj, endereco);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(enderecoMapper.toResponse(enderecoModel));
    }

    @Override
    @CacheEvict(value = {"enderecos", "endereco"}, key = "#cnpj", allEntries = true)
    public ResponseEntity<Void> updateEndereco(@PathVariable String cnpj,
                                               @PathVariable String id,
                                               @RequestBody @Valid EnderecoRequestDTO endereco) {
        empresaService.updateEndereco(cnpj, id, endereco);
        return ResponseEntity.ok().build();
    }

    @Override
    @CacheEvict(value = {"enderecos", "endereco"}, key = "#cnpj", allEntries = true)
    public ResponseEntity<Void> deleteEndereco(@PathVariable String cnpj,
                                               @PathVariable String id) {
        empresaService.deleteEndereco(cnpj, id);
        return ResponseEntity.ok().build();
    }
    // endregion Endereco endpoints
    // region Chave Pix
    @Override
    @Cacheable(value = "chavesPix", key = "#cnpj")
    public ResponseEntity<List<ChavePixResponseDTO>> getChaves(@PathVariable String cnpj) {
        List<ChavePixResponseDTO> chavesPix = chavePixMapper
                .toResponseList(empresaService.findChavesPixByCnpj(cnpj));

        return ResponseEntity.ok(chavesPix);
    }

    @Override
    @Cacheable(value = "chavePix", key = "#cnpj + ':' + #id")
    public ResponseEntity<ChavePixResponseDTO> getChave(@PathVariable String cnpj, @PathVariable String id) {
        ChavePixResponseDTO chavePix = chavePixMapper
                .toResponse(empresaService.findChavePixById(cnpj, id));

        return ResponseEntity.ok(chavePix);
    }

    @Override
    @CacheEvict(value = {"chavesPix", "chavePix"}, key = "#cnpj", allEntries = true)
    public ResponseEntity<ChavePixResponseDTO> addChave(@PathVariable String cnpj,
                                         @RequestBody @Valid ChavePixRequestDTO chavePixRequestDTO) {
        ChavePixResponseDTO response = chavePixMapper
                .toResponse(empresaService.addChavePix(cnpj, chavePixRequestDTO));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @Override
    @CacheEvict(value = {"chavesPix", "chavePix"}, key = "#cnpj", allEntries = true)
    public ResponseEntity<Void> updateChavePix(@PathVariable String cnpj,
                                               @PathVariable String id,
                                               @RequestBody @Valid ChavePixRequestDTO chavePixRequestDTO) {

        empresaService.updateChavePix(cnpj, id, chavePixRequestDTO);
        return ResponseEntity.ok().build();
    }

    @Override
    @CacheEvict(value = {"chavesPix", "chavePix"}, key = "#cnpj", allEntries = true)
    public ResponseEntity<Void> deleteChave(@PathVariable String cnpj,
                                            @PathVariable String id) {
        empresaService.deleteChavePix(cnpj, id);
        return ResponseEntity.ok().build();
    }

    // endregion Chave Pix

    // region Resíduo
    @Override
    @Cacheable(value = "residuos", key = "#cnpj")
    public ResponseEntity<List<ResiduoResponseDTO>> getResiduos(@PathVariable String cnpj) {
        return ResponseEntity.ok(residuoMapper.toResponseList(empresaService.findResiduosByCnpj(cnpj)));
    }

    @Override
    @Cacheable(value = "residuo", key = "#cnpj + ':' + #id")
    public ResponseEntity<ResiduoResponseDTO> getResiduo(@PathVariable String cnpj, @PathVariable String id) {
        return ResponseEntity.ok(residuoMapper.toResponse(empresaService.findResiduoById(cnpj, id)));
    }

    @Override
    @CacheEvict(value = {"residuos", "residuo"}, key = "#cnpj", allEntries = true)
    public ResponseEntity<ResiduoResponseDTO> addResiduo(@PathVariable String cnpj, @RequestBody @Valid ResiduoRequestDTO residuoRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(residuoMapper.toResponse(empresaService.addResiduo(cnpj, residuoRequestDTO)));
    }

    @Override
    @CacheEvict(value = {"residuos", "residuo"}, key = "#cnpj", allEntries = true)
    public ResponseEntity<Void> updateResiduo(@PathVariable String cnpj, @PathVariable String id, @RequestBody @Valid ResiduoRequestDTO residuoRequestDTO) {
        empresaService.updateResiduo(cnpj, id, residuoRequestDTO);
        return ResponseEntity.ok().build();
    }

    @Override
    @CacheEvict(value = {"residuos", "residuo"}, key = "#cnpj", allEntries = true)
    public ResponseEntity<Void> deleteResiduo(@PathVariable String cnpj, @PathVariable String id) {
        empresaService.deleteResiduo(cnpj, id);
        return ResponseEntity.ok().build();
    }
    // endregion Resíduo
}
