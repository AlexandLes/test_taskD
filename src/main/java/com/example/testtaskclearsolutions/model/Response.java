package com.example.testtaskclearsolutions.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response {
    private String timestamp;
    private String message;
    private int status;
}
