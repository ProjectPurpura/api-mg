package org.purpura.apimg.repository;

import org.purpura.apimg.model.empresa.EmpresaModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmpresaRepository extends MongoRepository<EmpresaModel, String> {
}
