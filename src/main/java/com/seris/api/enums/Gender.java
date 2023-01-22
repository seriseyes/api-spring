package com.seris.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Gender {
    male("Эрэгтэй"), female("Эмэгтэй");

    private final String mon;
}
