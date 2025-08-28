package org.purpura.apimg.search.empresa;

import org.purpura.apimg.model.empresa.EmpresaModel;
import org.purpura.apimg.search.base.KeywordSearcher;
import org.springframework.stereotype.Component;

@Component
public class EmpresaSearcher extends KeywordSearcher<EmpresaModel> {
    public EmpresaSearcher() {
        super(EmpresaModel.class);
    }

    // TODO: Override search and implement caching in redis for results.
}
