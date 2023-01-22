package com.seris.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.seris.api.enums.State;
import com.seris.api.util.pdf.StatefulDocument;
import lombok.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {
    private String message;
    private State state;
    private T data;
    private List<Field> fields;

    public static <T> Response<T> success(T data) {
        return Response.<T>builder().state(State.SUCCESS).data(data).build();
    }

    public static <T> Response<T> error(String message) {
        return Response.<T>builder().state(State.ERROR).message(message).build();
    }

    @SneakyThrows
    public static ResponseEntity<byte[]> pdf(StatefulDocument document) {
        String state = document.getState();
        byte[] contents = Files.readAllBytes(new File(state).toPath());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData(state, state);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        return new ResponseEntity<>(contents, headers, HttpStatus.OK);
    }
}
