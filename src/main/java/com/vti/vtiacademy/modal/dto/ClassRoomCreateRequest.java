package com.vti.vtiacademy.modal.dto;

import lombok.Data;

@Data
public class ClassRoomCreateRequest {
    private String name;
    private String address;
    private String note;
    private Integer size;
}
