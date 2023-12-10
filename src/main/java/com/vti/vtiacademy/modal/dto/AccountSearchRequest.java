package com.vti.vtiacademy.modal.dto;

import com.vti.vtiacademy.modal.entity.ClassStatus;
import com.vti.vtiacademy.modal.entity.TeachingForm;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AccountSearchRequest extends SearchBase{
    private String name;
}
