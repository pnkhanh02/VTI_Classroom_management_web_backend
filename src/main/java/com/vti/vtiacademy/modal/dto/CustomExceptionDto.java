package com.vti.vtiacademy.modal.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CustomExceptionDto {
    private LocalDateTime timestamp;
    private int status;
    private String message;
    private String path;

    public CustomExceptionDto(String message, int status, String path) {
        this.status = status;
        this.message = message;
        this.path = path;
        this.timestamp = LocalDateTime.now();
    }
}
