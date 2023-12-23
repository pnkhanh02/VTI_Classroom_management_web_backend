package com.vti.vtiacademy.modal.dto;

import com.vti.vtiacademy.modal.entity.Role;
import lombok.Data;

@Data
public class LoginDto {
    private Long id;
    private String username;
    private String fullName;
    private Role role;
    private String token; //có chứa chữ ký bí mật (để xác định token có từ hệ thống của mình hay không)
}
