package org.purpura.apimg.repository;

import org.purpura.apimg.model.empresa.EmpresaModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface EmpresaRepository extends MongoRepository<EmpresaModel, String> {
    Optional<EmpresaModel> findByUserHash(String hash);
}
