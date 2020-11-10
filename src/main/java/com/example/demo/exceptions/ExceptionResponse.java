package com.example.demo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
public class ExceptionResponse {

    @Getter private Date timestamp;
    @Getter private int status;
    @Getter private String error;
    @Getter private String message;
    @Getter private String path;
    @Getter private String exception;

}
