package com.vti.vtiacademy.modal.dto;

import com.vti.vtiacademy.modal.entity.ClassStatus;
import com.vti.vtiacademy.modal.entity.Role;
import com.vti.vtiacademy.modal.entity.TeachingForm;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
public class AccountCreateReq {
    @NotBlank(message = "User name không được để trống")
    @Length(max = 100, message = "Độ dài của username không hợp lệ")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "username không đúng định dạng")
    private String username;


    private LocalDate dateOfBirth;
    private String address;
    private String password;
    private String fullName;
    private Role role;
    private String phoneNumber;

    @NotBlank(message = "Email không được để trống")
    private String email;
    private String facebook;
    private String information;

    @Range(min = 1, max = 1000, message = "classEntityId phải nằm trong khoảng từ 1 -> 1000")
    private Long classEntityId;

}
