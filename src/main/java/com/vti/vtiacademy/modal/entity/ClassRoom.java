package com.vti.vtiacademy.modal.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "CLASS_ROOM")
@Data
public class ClassRoom {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true, columnDefinition = "nvarchar(200)")
    private String name;

    @Column(name = "address", nullable = false, columnDefinition = "nvarchar(500)")
    private String address;

    @Column(name = "note", columnDefinition = "nvarchar(500)")
    private String note;

    @Column(name = "size")
    private Integer size;
}
