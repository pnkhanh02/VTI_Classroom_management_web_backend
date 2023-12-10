package com.vti.vtiacademy.modal.dto;

import lombok.Data;

@Data
public class ZoomSearchRequest extends SearchBase{
    private String name;
    private String link;
    private String meetingId;

}
