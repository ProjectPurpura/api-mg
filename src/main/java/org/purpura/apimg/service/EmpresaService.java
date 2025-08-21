package org.purpura.apimg.service;

import org.purpura.apimg.dto.empresa.EmpresaRequestDTO;
import org.purpura.apimg.exception.DocumentNotFoundException;
import org.purpura.apimg.model.empresa.EmpresaModel;
import org.purpura.apimg.repository.EmpresaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpresaService {
    private final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    @Transactional
    public void save(EmpresaRequestDTO empresaRequestDTO) {
        EmpresaModel empresaModel = new EmpresaModel();
        BeanUtils.copyProperties(empresaModel, empresaRequestDTO);
        empresaRepository.save(empresaModel);
    }

    public EmpresaModel findById(String id) {
        return empresaRepository.findById(id).orElseThrow(() -> new DocumentNotFoundException(id));
    }
}
