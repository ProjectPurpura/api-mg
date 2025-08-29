package org.purpura.apimg.repository;

import org.purpura.apimg.model.empresa.EmpresaModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmpresaRepository extends MongoRepository<EmpresaModel, String> {
//    @Aggregation(pipeline = {
//            "{ '$match': { 'cnpj': ?0 } }",
//            "{ '$unwind': '$lsEnderecos' }",
//            "{ '$replaceRoot': { 'newRoot': '$lsEnderecos' } }"
//    })
//    List<EnderecoModel> findEnderecosByCnpj(String cnpj);
}
