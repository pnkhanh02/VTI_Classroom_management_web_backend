package com.vti.vtiacademy.modal.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "CLASS_ENTITY")
@Data
public class ClassEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "class_name", nullable = false, unique = true, columnDefinition = "nvarchar(100)")
    private String className;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ClassStatus classStatus;

    @Column(name = "teaching_form")
    @Enumerated(EnumType.STRING)
    private TeachingForm teachingForm;

    //Khóa ngoại tới account (mentorID)
    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private Account mentor;

    //Khóa ngoại tới zoom
    @ManyToOne
    @JoinColumn(name = "zoom_id")
    private Zoom zoom;

    //Khóa ngoại tới class Room
    @ManyToOne
    @JoinColumn(name = "class_room_id")
    private ClassRoom classRoom;

    @Column(name = "description", columnDefinition = "nvarchar(500)")
    private String description;


}
