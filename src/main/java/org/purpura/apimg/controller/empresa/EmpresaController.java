package org.purpura.apimg.controller.empresa;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.purpura.apimg.controller.empresa.oas.ChavePixContract;
import org.purpura.apimg.controller.empresa.oas.EmpresaContract;
import org.purpura.apimg.controller.empresa.oas.EnderecoContract;
import org.purpura.apimg.controller.empresa.oas.ResiduoContract;
import org.purpura.apimg.dto.mapper.empresa.ChavePixMapper;
import org.purpura.apimg.dto.mapper.empresa.EmpresaMapper;
import org.purpura.apimg.dto.mapper.empresa.EnderecoMapper;
import org.purpura.apimg.dto.schemas.empresa.base.EmpresaRequestDTO;
import org.purpura.apimg.dto.schemas.empresa.base.EmpresaResponseDTO;
import org.purpura.apimg.dto.schemas.empresa.endereco.EnderecoResponseDTO;
import org.purpura.apimg.dto.schemas.empresa.pix.ChavePixRequestDTO;
import org.purpura.apimg.dto.schemas.empresa.pix.ChavePixResponseDTO;
import org.purpura.apimg.dto.schemas.empresa.residuo.ResiduoRequestDTO;
import org.purpura.apimg.dto.schemas.empresa.residuo.ResiduoResponseDTO;
import org.purpura.apimg.search.base.SearchKeywords;
import org.purpura.apimg.service.EmpresaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.purpura.apimg.dto.schemas.empresa.endereco.EnderecoRequestDTO;
import org.purpura.apimg.model.empresa.EnderecoModel;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;

@RestController
@RequestMapping("/empresa")
@RequiredArgsConstructor
public class EmpresaController implements EmpresaContract, EnderecoContract, ResiduoContract, ChavePixContract {

    private final EmpresaService empresaService;
    private final EnderecoMapper enderecoMapper;
    private final ChavePixMapper chavePixMapper;

    // region EMPRESA
    @Override
    @CacheEvict(value = {"empresas", "empresa"}, allEntries = true)
    public void save(@RequestBody @Valid EmpresaRequestDTO empresaRequestDTO) {
        empresaService.insert(empresaRequestDTO);
    }

    @Override
    @Cacheable(value = "empresa", key = "#cnpj")
    public EmpresaResponseDTO get(@PathVariable String cnpj) {
        return empresaService.getByCnpj(cnpj);
    }

    @Override
    @CacheEvict(value = {"empresas", "empresa"}, allEntries = true)
    public void delete(@PathVariable String cnpj) {
        empresaService.deleteByCnpj(cnpj);
    }

    @Override
    @CacheEvict(value = {"empresas", "empresa"}, allEntries = true)
    public void update(@PathVariable String cnpj,
                      @RequestBody @Valid EmpresaRequestDTO empresaUpdateRequestDTO) {
        empresaService.update(cnpj, empresaUpdateRequestDTO);
    }

    @Override
    @Cacheable(value = "empresas")
    public List<EmpresaResponseDTO> findAll() {
        return empresaService.getAll();
    }

    @Override
    @Cacheable(value = "empresas", key = "#query")
    public List<EmpresaResponseDTO> search(@RequestParam @SearchKeywords @Valid String query) {
        return empresaService.search(query);
    }
    // endregion EMPRESA
    // region Endereco endpoints
    @Override
    @Cacheable(value = "enderecos", key = "#cnpj")
    public List<EnderecoResponseDTO> getEnderecos(@PathVariable String cnpj) {
        return enderecoMapper.toResponseList(empresaService.findEnderecosByCnpj(cnpj));
    }

    @Override
    @Cacheable(value = "endereco", key = "#cnpj + ':' + #id")
    public EnderecoResponseDTO getEndereco(@PathVariable String cnpj, @PathVariable String id) {
        return enderecoMapper.toResponse(empresaService.findEnderecoById(cnpj, id));
    }

    @Override
    @CacheEvict(value = {"enderecos", "endereco"}, key = "#cnpj", allEntries = true)
    public EnderecoResponseDTO addEndereco(@PathVariable String cnpj,
                                           @RequestBody @Valid EnderecoRequestDTO endereco) {
        EnderecoModel enderecoModel = empresaService.addEndereco(cnpj, endereco);
        return enderecoMapper.toResponse(enderecoModel);
    }

    @Override
    @CacheEvict(value = {"enderecos", "endereco"}, key = "#cnpj", allEntries = true)
    public void updateEndereco(@PathVariable String cnpj,
                              @PathVariable String id,
                              @RequestBody @Valid EnderecoRequestDTO endereco) {
        empresaService.updateEndereco(cnpj, id, endereco);
    }

    @Override
    @CacheEvict(value = {"enderecos", "endereco"}, key = "#cnpj", allEntries = true)
    public void deleteEndereco(@PathVariable String cnpj,
                              @PathVariable String id) {
        empresaService.deleteEndereco(cnpj, id);
    }
    // endregion Endereco endpoints
    // region Chave Pix
    @Override
    @Cacheable(value = "chavesPix", key = "#cnpj")
    public List<ChavePixResponseDTO> getChaves(@PathVariable String cnpj) {
        return chavePixMapper.toResponseList(empresaService.findChavesPixByCnpj(cnpj));
    }

    @Override
    @Cacheable(value = "chavePix", key = "#cnpj + ':' + #id")
    public ChavePixResponseDTO getChave(@PathVariable String cnpj, @PathVariable String id) {
        return chavePixMapper.toResponse(empresaService.findChavePixById(cnpj, id));
    }

    @Override
    @CacheEvict(value = {"chavesPix", "chavePix"}, key = "#cnpj", allEntries = true)
    public ChavePixResponseDTO addChave(@PathVariable String cnpj,
                                        @RequestBody @Valid ChavePixRequestDTO chavePixRequestDTO) {
        return chavePixMapper.toResponse(empresaService.addChavePix(cnpj, chavePixRequestDTO));
    }

    @Override
    @CacheEvict(value = {"chavesPix", "chavePix"}, key = "#cnpj", allEntries = true)
    public void updateChavePix(@PathVariable String cnpj,
                              @PathVariable String id,
                              @RequestBody @Valid ChavePixRequestDTO chavePixRequestDTO) {
        empresaService.updateChavePix(cnpj, id, chavePixRequestDTO);
    }

    @Override
    @CacheEvict(value = {"chavesPix", "chavePix"}, key = "#cnpj", allEntries = true)
    public void deleteChave(@PathVariable String cnpj,
                            @PathVariable String id) {
        empresaService.deleteChavePix(cnpj, id);
    }
    // endregion Chave Pix
    // region Resíduo

    @Override
    @Cacheable(value = "residuo", key = "#cnpj + ':' + #id")
    public ResiduoResponseDTO getResiduo(@PathVariable String cnpj, @PathVariable String id) {
        return empresaService.getResiduo(cnpj, id);
    }

    @Override
    @Cacheable(value = "residuos", key = "#cnpj")
    public List<ResiduoResponseDTO> getAllResiduosByCnpj(@PathVariable String cnpj) {
        return empresaService.getAllResiduosByCnpj(cnpj);
    }

    @Override
    public List<ResiduoResponseDTO> getAllResiduosView(@PathVariable String cnpj, @RequestParam Integer limit, @RequestParam Integer page) {
        return empresaService.getAllResiduosView(cnpj, limit, page);
    }

    @Override
    @CacheEvict(value = {"residuos", "residuo"}, key = "#cnpj", allEntries = true)
    public ResiduoResponseDTO addResiduo(@PathVariable String cnpj, @RequestBody @Valid ResiduoRequestDTO residuoRequestDTO) {
        return empresaService.addResiduo(cnpj, residuoRequestDTO);
    }

    @Override
    @CacheEvict(value = {"residuos", "residuo"}, key = "#cnpj", allEntries = true)
    public void updateResiduo(@PathVariable String cnpj, @PathVariable String id, @RequestBody @Valid ResiduoRequestDTO residuoRequestDTO) {
        empresaService.updateResiduo(cnpj, id, residuoRequestDTO);
    }

    @Override
    @CacheEvict(value = {"residuos", "residuo"}, key = "#cnpj", allEntries = true)
    public void deleteResiduo(@PathVariable String cnpj, @PathVariable String id) {
        empresaService.deleteResiduo(cnpj, id);
    }
    // endregion Resíduo
}
