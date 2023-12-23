package com.vti.vtiacademy.modal.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class ZoomCreateRequest {
    @NotBlank(message = "User name không được để trống")
    @Length(max = 100, message = "Độ dài của username không hợp lệ")
    private String name;
    private String link;
    private String description;
    private String meetingId;
    private String passCode;
}
