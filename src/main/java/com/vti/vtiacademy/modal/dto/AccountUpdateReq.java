package com.vti.vtiacademy.modal.dto;

import com.vti.vtiacademy.modal.entity.ClassStatus;
import com.vti.vtiacademy.modal.entity.Role;
import com.vti.vtiacademy.modal.entity.TeachingForm;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AccountUpdateReq {
    private Long accountId;
    private String username;
    private LocalDate dateOfBirth;
    private String address;
//    private String password;
    private String fullName;
    private Role role;
    private String phoneNumber;
    private String email;
    private String facebook;
    private String information;
    private Long classEntityId;

}
