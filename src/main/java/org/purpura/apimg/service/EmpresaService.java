package org.purpura.apimg.service;

import lombok.RequiredArgsConstructor;
import org.purpura.apimg.dto.mapper.empresa.ChavePixMapper;
import org.purpura.apimg.dto.mapper.empresa.EmpresaMapper;
import org.purpura.apimg.dto.mapper.empresa.EnderecoMapper;
import org.purpura.apimg.dto.mapper.empresa.ResiduoMapper;
import org.purpura.apimg.dto.schemas.empresa.base.EmpresaRequestDTO;
import org.purpura.apimg.dto.schemas.empresa.base.EmpresaResponseDTO;
import org.purpura.apimg.dto.schemas.empresa.endereco.EnderecoRequestDTO;
import org.purpura.apimg.dto.schemas.empresa.pix.ChavePixRequestDTO;
import org.purpura.apimg.dto.schemas.empresa.residuo.ResiduoRequestDTO;
import org.purpura.apimg.dto.schemas.empresa.residuo.ResiduoResponseDTO;
import org.purpura.apimg.exception.empresa.EmpresaNotFoundException;
import org.purpura.apimg.exception.empresa.EnderecoNotFoundException;
import org.purpura.apimg.exception.empresa.ChavePixNotFoundException;
import org.purpura.apimg.exception.empresa.ResiduoNotFoundException;
import org.purpura.apimg.model.empresa.ChavePixModel;
import org.purpura.apimg.model.empresa.EmpresaModel;
import org.purpura.apimg.model.empresa.EnderecoModel;
import org.purpura.apimg.model.empresa.ResiduoModel;
import org.purpura.apimg.repository.EmpresaRepository;
import org.purpura.apimg.search.empresa.EmpresaSearcher;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpresaService {
    private final EmpresaRepository empresaRepository;
    private final EmpresaSearcher empresaSearcher;
    private final ResiduoMapper residuoMapper;
    private final EmpresaMapper empresaMapper;
    private final ChavePixMapper chavePixMapper;
    private final EnderecoMapper enderecoMapper;

    // region EMPRESA

    @Transactional
    public void insert(EmpresaRequestDTO empresaRequestDTO) {
        EmpresaModel empresaModel = empresaMapper.toModel(empresaRequestDTO);
        empresaRepository.save(empresaModel);
    }

    @Transactional
    public void update(String cnpj, EmpresaRequestDTO empresaRequestDTO) {
        EmpresaModel empresaModel = findByCnpj(cnpj);
        BeanUtils.copyProperties(empresaRequestDTO, empresaModel);
        empresaRepository.save(empresaModel);
    }


    public EmpresaModel findByCnpj(String cnpj) {
        return empresaRepository.findById(cnpj)
                .orElseThrow(() -> new EmpresaNotFoundException(cnpj));
    }

    public EmpresaResponseDTO getByCnpj(String cnpj) {
        return empresaMapper.toResponse(findByCnpj(cnpj));
    }

    private List<EmpresaModel> findAll() {
        return empresaRepository.findAll();
    }

    public List<EmpresaResponseDTO> getAll() {
        return empresaMapper
                .toResponseList(findAll().stream().toList());
    }


    @Transactional
    public void deleteByCnpj(String cnpj) {
        empresaRepository.delete(findByCnpj(cnpj));
    }

    public List<EmpresaResponseDTO> search(String keywords) {
        return empresaMapper
                .toResponseList(empresaSearcher.search(findAll(), keywords));
    }

    // endregion EMPRESA

    // region ENDERECO


    private EnderecoModel findEnderecoById(String cnpj, String id, EmpresaModel empresaModel) {
        return empresaModel.getEnderecos().stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new EnderecoNotFoundException(cnpj, id));
    }

    public EnderecoModel findEnderecoById(String cnpj, String id) {
        EmpresaModel empresaModel = findByCnpj(cnpj);
        return findEnderecoById(cnpj, id, empresaModel);
    }

    public List<EnderecoModel> findEnderecosByCnpj(String cnpj) {
        EmpresaModel empresaModel = findByCnpj(cnpj);
        return empresaModel.getEnderecos();
    }

        public EnderecoModel addEndereco(String cnpj, EnderecoRequestDTO endereco) {
        EmpresaModel empresaModel = findByCnpj(cnpj);

        EnderecoModel enderecoModel = new EnderecoModel();
        BeanUtils.copyProperties(endereco, enderecoModel);
        enderecoModel.setId(java.util.UUID.randomUUID().toString());

        empresaModel.getEnderecos().add(enderecoModel);
        empresaRepository.save(empresaModel);

        return enderecoModel;
    }

    public void deleteEndereco(String cnpj, String id) {
        EmpresaModel empresaModel = findByCnpj(cnpj);
        EnderecoModel enderecoModel = findEnderecoById(cnpj, id, empresaModel);

        empresaModel.getEnderecos().remove(enderecoModel);
        empresaRepository.save(empresaModel);
    }


    public void updateEndereco(String cnpj, String id, EnderecoRequestDTO endereco) {
        EmpresaModel empresaModel = findByCnpj(cnpj);

        EnderecoModel enderecoModel = findEnderecoById(cnpj, id, empresaModel);
        
        BeanUtils.copyProperties(endereco, enderecoModel);
        empresaRepository.save(empresaModel);
    }

    // endregion ENDERECO

    // region CHAVE PIX

    private ChavePixModel findChavePixById(String cnpj, String id, EmpresaModel empresaModel) {
        return empresaModel.getChavesPix().stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ChavePixNotFoundException(cnpj, id));
    }

    public ChavePixModel findChavePixById(String cnpj, String id) {
        EmpresaModel empresaModel = findByCnpj(cnpj);
        return findChavePixById(cnpj, id, empresaModel);
    }

    public ChavePixModel addChavePix(String cnpj, ChavePixRequestDTO chavePixRequestDTO) {
        EmpresaModel empresaModel = findByCnpj(cnpj);
        ChavePixModel model = new ChavePixModel();
        BeanUtils.copyProperties(chavePixRequestDTO, model);
        model.setId(java.util.UUID.randomUUID().toString());
        empresaModel.getChavesPix().add(model);
        empresaRepository.save(empresaModel);

        return model;
    }

    public void deleteChavePix(String cnpj, String id) {
        EmpresaModel empresaModel = findByCnpj(cnpj);
        ChavePixModel chavePixModel = findChavePixById(cnpj, id, empresaModel);
        empresaModel.getChavesPix().remove(chavePixModel);
        empresaRepository.save(empresaModel);
    }

    public List<ChavePixModel> findChavesPixByCnpj(String cnpj) {
        EmpresaModel empresaModel = findByCnpj(cnpj);
        return empresaModel.getChavesPix();
    }

    public void updateChavePix(String cnpj, String id, ChavePixRequestDTO chavePixRequestDTO) {
        EmpresaModel empresaModel = findByCnpj(cnpj);
        ChavePixModel chavePixModel = findChavePixById(cnpj, id, empresaModel);
        BeanUtils.copyProperties(chavePixRequestDTO, chavePixModel);
        empresaRepository.save(empresaModel);
    }
    // endregion CHAVE PIX

    // region RESÍDUO
    private ResiduoModel findResiduoById(String id, EmpresaModel empresaModel) {
        return empresaModel.getResiduos().stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResiduoNotFoundException(empresaModel.getCnpj(), id));
    }

    private ResiduoModel findResiduoById(String id, String cnpj) {
        EmpresaModel empresaModel = findByCnpj(cnpj);
        return findResiduoById(id, empresaModel);
    }

    public ResiduoResponseDTO getResiduo(String cnpj, String id) {
        return residuoMapper
                .toResponse(findResiduoById(id, cnpj), cnpj);
    }

    public ResiduoResponseDTO addResiduo(String cnpj, ResiduoRequestDTO residuoRequestDTO) {
        EmpresaModel empresaModel = findByCnpj(cnpj);
        ResiduoModel residuoModel = residuoMapper.toModel(residuoRequestDTO);
        empresaModel.getResiduos().add(residuoModel);
        empresaRepository.save(empresaModel);

        return residuoMapper.toResponse(residuoModel, empresaModel.getCnpj());
    }

    public void deleteResiduo(String cnpj, String id) {
        EmpresaModel empresaModel = findByCnpj(cnpj);
        ResiduoModel residuoModel = findResiduoById(id, empresaModel);
        empresaModel.getResiduos().remove(residuoModel);
        empresaRepository.save(empresaModel);
    }


    public void updateResiduo(String cnpj, String id, ResiduoRequestDTO residuoRequestDTO) {
        EmpresaModel empresaModel = findByCnpj(cnpj);
        ResiduoModel residuoModel = findResiduoById(id, empresaModel);
        BeanUtils.copyProperties(residuoRequestDTO, residuoModel);
        empresaRepository.save(empresaModel);
    }


    public List<ResiduoResponseDTO> getAllResiduosByCnpj(String cnpj) {
        EmpresaModel empresaModel = findByCnpj(cnpj);
        return residuoMapper
                .toResponseList(empresaModel.getResiduos(), empresaModel.getCnpj());
    }

    public List<ResiduoResponseDTO> getAllResiduosView(String cnpj, Integer limit, Integer currentPage) {
        List<EmpresaModel> empresaModels = findAll();

        int skip = (currentPage != null && limit != null) ? (currentPage - 1) * limit : 0;
        int max = (limit != null) ? limit : Integer.MAX_VALUE;

        return empresaModels.stream()
                .filter(e -> !e.getCnpj().equals(cnpj))
                .flatMap(empresaModel ->
                        empresaModel.getResiduos().stream()
                                .map(r -> residuoMapper.toResponse(r, empresaModel.getCnpj()))
                )
                .skip(skip)
                .limit(max)
                .toList();
    }
    // endregion RESÍDUO
}
