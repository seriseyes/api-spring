package com.seris.api.model;

import com.seris.api.enums.State;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Validation<T> {
    private State state;
    @Getter
    private String message;
    private List<Field> fields;

    public Validation(State state, String message) {
        this.state = state;
        this.message = message;
    }

    public static <T> Validation<T> error(String message) {
        return new Validation<>(State.ERROR, message);
    }

    public static <T> Validation<T> success() {
        return new Validation<>(State.SUCCESS, null);
    }

    public boolean isError() {
        return state == State.ERROR;
    }

    public Response<T> toResponse() {
        return Response.<T>builder().state(isError() ? State.ERROR : State.SUCCESS).message(message).fields(fields).build();
    }
}
