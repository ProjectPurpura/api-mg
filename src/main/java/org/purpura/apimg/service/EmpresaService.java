package org.purpura.apimg.service;

import org.purpura.apimg.dto.empresa.EmpresaSaveRequestDTO;
import org.purpura.apimg.dto.empresa.EmpresaUpdateRequestDTO;
import org.purpura.apimg.dto.empresa.endereco.EnderecoRequestDTO;
import org.purpura.apimg.dto.empresa.pix.ChavePixRequestDTO;
import org.purpura.apimg.exception.EmpresaNotFoundException;
import org.purpura.apimg.exception.EnderecoNotFoundException;
import org.purpura.apimg.exception.ChavePixNotFoundException;
import org.purpura.apimg.model.empresa.ChavePixModel;
import org.purpura.apimg.model.empresa.EmpresaModel;
import org.purpura.apimg.model.empresa.EnderecoModel;
import org.purpura.apimg.repository.EmpresaRepository;
import org.purpura.apimg.search.empresa.EmpresaSearcher;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmpresaService {
    private final EmpresaRepository empresaRepository;
    private final EmpresaSearcher empresaSearcher;

    public EmpresaService(EmpresaRepository empresaRepository, EmpresaSearcher empresaSearcher) {
        this.empresaRepository = empresaRepository;
        this.empresaSearcher = empresaSearcher;
    }
    // region EMPRESA

    @Transactional
    public void insert(EmpresaSaveRequestDTO empresaSaveRequestDTO) {
        EmpresaModel empresaModel = new EmpresaModel();
        BeanUtils.copyProperties(empresaSaveRequestDTO, empresaModel);
        empresaRepository.save(empresaModel);
    }

    @Transactional
    public void update(String cnpj, EmpresaUpdateRequestDTO empresaUpdateRequestDTO) {
        EmpresaModel empresaModel = findByCnpj(cnpj);
        BeanUtils.copyProperties(empresaUpdateRequestDTO, empresaModel);
        empresaRepository.save(empresaModel);
    }


    public EmpresaModel findByCnpj(String cnpj) {
        return empresaRepository.findById(cnpj)
                .orElseThrow(() -> new EmpresaNotFoundException(cnpj));
    }

    public List<EmpresaModel> findAll() {
        return empresaRepository.findAll();
    }


    @Transactional
    public void deleteByCnpj(String cnpj) {
        empresaRepository.delete(findByCnpj(cnpj));
    }

    public List<EmpresaModel> search(String keywords) {
        return empresaSearcher.search(findAll(), keywords);
    }

    // endregion EMPRESA

    // region ENDERECO

    public List<EnderecoModel> findEnderecosByCnpj(String cnpj) {
        EmpresaModel empresaModel = findByCnpj(cnpj);
        return empresaModel.getEnderecos();
    }

    public void addEndereco(String cnpj, EnderecoRequestDTO endereco) {
        EmpresaModel empresaModel = findByCnpj(cnpj);

        EnderecoModel enderecoModel = new EnderecoModel();
        BeanUtils.copyProperties(endereco, enderecoModel);

        if (empresaModel.getEnderecos() == null) {
            empresaModel.setEnderecos(List.of(enderecoModel));
        }

        empresaModel.getEnderecos().add(enderecoModel);
        empresaRepository.save(empresaModel);
    }

    public void deleteEndereco(String cnpj, String id) {
        EmpresaModel empresaModel = findByCnpj(cnpj);
        empresaModel.getEnderecos().removeIf(endereco -> endereco.getId().equals(id));
    }


    public void updateEndereco(String cnpj, String id, EnderecoRequestDTO endereco) {
        EmpresaModel empresaModel = findByCnpj(cnpj);

        EnderecoModel enderecoModel = empresaModel.getEnderecos().stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new EnderecoNotFoundException(cnpj, id));
        
        BeanUtils.copyProperties(endereco, enderecoModel);
        empresaRepository.save(empresaModel);
    }

    // endregion ENDERECO
    // region CHAVE PIX

    public void addChavePix(String cnpj, ChavePixRequestDTO chavePixRequestDTO) {
        EmpresaModel empresaModel = findByCnpj(cnpj);
        chavePixRequestDTO.setChave(chavePixRequestDTO.getChave());

        ChavePixModel model = new ChavePixModel();
        BeanUtils.copyProperties(chavePixRequestDTO, model);

        empresaModel.getChavesPix().add(model);
        empresaRepository.save(empresaModel);
    }

    public void deleteChavePix(String cnpj, String chave) {
        EmpresaModel empresaModel = findByCnpj(cnpj);
        empresaModel.getChavesPix().removeIf(chavePixModel -> chavePixModel.getChave().equals(chave));
        empresaRepository.save(empresaModel);
    }

    public List<ChavePixModel> findChavesPixByCnpj(String cnpj) {
        EmpresaModel empresaModel = findByCnpj(cnpj);
        return empresaModel.getChavesPix();
    }

    public void updateChavePix(String cnpj, String chave, ChavePixRequestDTO chavePixRequestDTO) {
        EmpresaModel empresaModel = findByCnpj(cnpj);
        
        ChavePixModel chavePixModel = empresaModel.getChavesPix().stream()
                .filter(c -> c.getChave().equals(chave))
                .findFirst()
                .orElseThrow(() -> new ChavePixNotFoundException(cnpj, chave));
        
        BeanUtils.copyProperties(chavePixRequestDTO, chavePixModel);
        empresaRepository.save(empresaModel);
    }

    // endregion CHAVE PIX
}
