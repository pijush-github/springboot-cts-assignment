package com.springboot.assignment.util;

public interface EntityModelConverter<E, M> {

    public abstract E convertToEntity(M model);
    public abstract M convertToModel(E entity);
}
