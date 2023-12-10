package com.vti.vtiacademy.modal.dto;

import lombok.Data;

@Data
public class ZoomUpdateRequest {
    private Long id;
    private String name;
    private String link;
    private String description;
    private String meetingId;
    private String passCode;
}
