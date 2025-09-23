package org.purpura.apimg.dto.mapper.base;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class BeanUtilMapper<M, I, O> extends BaseMapper<M, I, O> {

    private final Class<M> modelClass;
    private final Class<O> responseClass;

    public BeanUtilMapper(Class<M> modelClass, Class<O> responseClass) {
        this.modelClass = modelClass;
        this.responseClass = responseClass;
    }

    private <S, T> T convert(S source, Class<T> targetClass) {
        try {
            T target = targetClass.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, target);
            return target;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public O toResponse(M model) {
        return convert(model, responseClass);
    }

    @Override
    public M toModel(I request) {
        return convert(request, modelClass);
    }
}
