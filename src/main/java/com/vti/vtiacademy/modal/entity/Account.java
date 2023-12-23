package com.vti.vtiacademy.modal.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Account")
@Data
public class Account {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "username", length = 100, nullable = false, unique = true)
    private String username;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "address", columnDefinition = "nvarchar(500)")
    private String address;

    @Column(name = "password", length = 100, nullable = false) // có chứa ký tự đặc biệt, có chữ hoa, chữ thường, ko tiếng việt
    private String password;

    @Column(name = "full_name", columnDefinition = "nvarchar(200)", nullable = false) // có chứa ký tự đặc biệt, có chữ hoa, chữ thường, ko tiếng việt
    private String fullName;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "phone_number", nullable = false, unique = true, length = 12)
    private String phoneNumber;

    @Column(name = "email", nullable = false, unique = true, length = 200)
    private String email;

    @Column(name = "facebook", nullable = false, unique = true, length = 200)
    private String facebook;

    @Column(name = "information", columnDefinition = "nvarchar(200)")
    private String information;

    //Khóa ngoại tới bảng class
    @ManyToOne
    @JoinColumn(name = "class_entity_id")
    private ClassEntity classEntity;

}
