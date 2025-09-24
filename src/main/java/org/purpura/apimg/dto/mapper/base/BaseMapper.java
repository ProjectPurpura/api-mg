package org.purpura.apimg.dto.mapper.base;

import java.util.List;

public abstract class BaseMapper<M, I, O> {
    abstract public O toResponse(M model);
    abstract public M toModel(I request);

    public List<O> toResponseList(List<M> models) {
        return models.stream().map(this::toResponse).toList();
    }

    public List<M> toModelList(List<I> requests) {
        return requests.stream().map(this::toModel).toList();
    }
}
