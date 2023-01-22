package com.seris.api.util;

import com.seris.api.annotations.Attribute;
import com.seris.api.enums.State;
import com.seris.api.model.Validation;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.SneakyThrows;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class Validator {

    @SneakyThrows
    public <T> Validation<T> validateEntity(T entity) {
        if (entity == null) return Validation.error("Entity null байна.");

        List<Field> fields = Stream.of(entity.getClass().getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(NotNull.class)
                        || f.isAnnotationPresent(NotEmpty.class)
                        || f.isAnnotationPresent(Length.class)
                        || f.isAnnotationPresent(Attribute.class)
                )
                .toList();

        Function<Field, Validation<T>> nullMessage = field -> {
            if (field.isAnnotationPresent(Attribute.class)) {
                return Validation.error(field.getAnnotation(Attribute.class).value() + " тодорхойгүй байна.");
            } else {
                return Validation.error(field.getName() + " тодорхойгүй байна.");
            }
        };

        List<com.seris.api.model.Field> errorFields = new ArrayList<>();

        for (Field field : fields) {
            field.setAccessible(true);
            Attribute annotation = field.getAnnotation(Attribute.class);
            if (annotation != null && annotation.skip()) continue;

            if (field.isAnnotationPresent(NotNull.class) && field.get(entity) == null) {
                errorFields.add(new com.seris.api.model.Field(field.getName(), nullMessage.apply(field).getMessage()));
                continue;
            }

            if (field.isAnnotationPresent(NotEmpty.class)) {
                Object value = field.get(entity);
                if (value == null || value.toString().trim().isEmpty()) {
                    errorFields.add(new com.seris.api.model.Field(field.getName(), nullMessage.apply(field).getMessage()));
                    continue;
                }
            }

            if (field.isAnnotationPresent(Length.class)) {
                Length length = field.getAnnotation(Length.class);
                String message = annotation != null ? annotation.value() : field.getName();
                Object value = field.get(entity);
                if (value == null) value = "";

                if (value.toString().length() > length.max()) {
                    errorFields.add(new com.seris.api.model.Field(field.getName(), message + " " + length.max() + " тэмдэгтээс илүүгүй байх ёстой"));
                    continue;
                }
                if (value.toString().length() < length.min()) {
                    errorFields.add(new com.seris.api.model.Field(field.getName(), message + " " + length.min() + " тэмдэгтээс багагүй байх ёстой"));
                    continue;
                }
            }

            if (annotation != null) {
                if (annotation.trim()) field.set(entity, field.get(entity).toString().trim());
                if (annotation.capitalize()) field.set(entity, StringUtils.capitalize(field.get(entity).toString()));
                if (annotation.upper()) field.set(entity, field.get(entity).toString().toUpperCase());
                if (annotation.lower()) field.set(entity, field.get(entity).toString().toLowerCase());
                if (annotation.noSpace()) field.set(entity, field.get(entity).toString().replaceAll(" ", ""));
                if (annotation.notZero()) {
                    int i = Integer.parseInt(field.get(entity).toString());
                    if (i <= 0) {
                        errorFields.add(new com.seris.api.model.Field(field.getName(), annotation.value() + (i == 0 ? " 0 байж болохгүй" : " сөрөг тоо байж болохгүй")));
                    }
                }
            }
        }

        return errorFields.isEmpty() ? Validation.success() : Validation.<T>builder().state(State.ERROR).message("Шаардлагын дагуу бөглөнө үү").fields(errorFields).build();
    }
}
