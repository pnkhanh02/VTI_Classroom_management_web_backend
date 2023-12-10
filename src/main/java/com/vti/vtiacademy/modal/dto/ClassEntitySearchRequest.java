package com.vti.vtiacademy.modal.dto;

import com.vti.vtiacademy.modal.entity.ClassStatus;
import com.vti.vtiacademy.modal.entity.TeachingForm;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ClassEntitySearchRequest extends SearchBase{
    private String className;
    private LocalDate startDateMin;
    private LocalDate endDateMin;
    private ClassStatus classStatus;
    private TeachingForm teachingForm;
    private Long zoomId;
    private Long classRoomId;

}
