package ru.aora.erp.presentation.entity.dto.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public final class MapperUtils {

    public static <T, R> List<R> convert(List<T> entities, Function<T, R> converter) {
        if (entities == null) {
            throw new IllegalArgumentException("List of entities should not be NULL");
        }
        if (converter == null) {
            throw new IllegalArgumentException("Entity converter should not be NULL");
        }
        List<R> convertedEntities = new ArrayList<>(entities.size());
        for (T entity : entities) {
            convertedEntities.add(converter.apply(entity));
        }
        return convertedEntities;
    }
}
