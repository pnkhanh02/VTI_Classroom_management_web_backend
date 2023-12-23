package com.vti.vtiacademy.modal.dto;

import com.vti.vtiacademy.modal.entity.Role;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
public class RegisterAccount {
    private String username;
    private LocalDate dateOfBirth;
    private String address;
    private String password;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String facebook;

}
