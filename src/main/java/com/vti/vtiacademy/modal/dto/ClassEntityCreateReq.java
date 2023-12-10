package com.vti.vtiacademy.modal.dto;

import com.vti.vtiacademy.modal.entity.ClassStatus;
import com.vti.vtiacademy.modal.entity.TeachingForm;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ClassEntityCreateReq {
    private String className;
    private LocalDate startDate;
    private LocalDate endDate;
    private ClassStatus classStatus;
    private TeachingForm teachingForm;
    private String description;

    private Long accountId; //findAccountById -> lấy ra đối tượng
    private Long zoomId;
    private Long classRoomId;
}
