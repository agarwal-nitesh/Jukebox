package com.nitesh.jukebox.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Response<T> implements Serializable {
    private String status;

    private int statusCode;

    private String message;

    private String desc;

    private String debug;

    private String exception;

    private T data;
}