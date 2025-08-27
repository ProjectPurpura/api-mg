package org.purpura.apimg.service;

import org.purpura.apimg.dto.empresa.EmpresaSaveRequestDTO;
import org.purpura.apimg.dto.empresa.EmpresaUpdateRequestDTO;
import org.purpura.apimg.dto.endereco.EnderecoRequestDTO;
import org.purpura.apimg.exception.EmpresaNotFoundException;
import org.purpura.apimg.model.empresa.EmpresaModel;
import org.purpura.apimg.model.empresa.EnderecoModel;
import org.purpura.apimg.repository.EmpresaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmpresaService {
    private final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
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

    // endregion EMPRESA

    // region ENDERECO

    public List<EnderecoModel> findEnderecosByCnpj(String cnpj) {
        return findByCnpj(cnpj).getEnderecos();
    }

    public void addEndereco(String cnpj, EnderecoRequestDTO endereco) {
        EmpresaModel empresaModel = findByCnpj(cnpj);

        EnderecoModel enderecoModel = new EnderecoModel();
        BeanUtils.copyProperties(endereco, enderecoModel);

        empresaModel.getEnderecos().add(enderecoModel);
        empresaRepository.save(empresaModel);
    }

    public void deleteEndereco(String cnpj, String id) {
        EmpresaModel empresaModel = findByCnpj(cnpj);
        empresaModel.getEnderecos().removeIf(endereco -> endereco.getId().equals(id));
    }

    public void updateEndereco(String cnpj, String id, EnderecoRequestDTO endereco) {
        EmpresaModel empresaModel = findByCnpj(cnpj);
        empresaModel.getEnderecos().stream()
                .filter(enderecoModel -> enderecoModel.getId().equals(id))
                .findFirst()
                .ifPresent(enderecoModel -> BeanUtils.copyProperties(endereco, enderecoModel));
    }
    // endregion ENDERECO

    // region CHAVE PIX

    // endregion CHAVE PIX
}
