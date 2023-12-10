package com.vti.vtiacademy.modal.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ZOOM")
@Data
public class Zoom {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name;

    @Column(name = "link", nullable = false, unique = true, length = 200)
    private String link;

    @Column(name = "description", columnDefinition = "nvarchar(500)")
    private String description;

    @Column(name = "meeting_id", nullable = false, unique = true, length = 50)
    private String meetingId;

    @Column(name = "pass_code", nullable = false, length = 50)
    private String passCode;
}
