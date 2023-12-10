package com.vti.vtiacademy.modal.dto;

import lombok.Data;

@Data
public class ClassRoomSearch extends SearchBase{
    private String name; //= like
    private String address;
    private Integer minSize;
    private Integer maxSize;


}
