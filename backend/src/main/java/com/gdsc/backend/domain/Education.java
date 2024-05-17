package com.gdsc.backend.domain;

import jakarta.persistence.*;

public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "educationID", updatable = false)
    private Long educationID;

    @Column(name = "educationLevel", nullable = false)
    private String educationnLevel;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "grade", nullable = false)
    private Double grade;

    @Column(name = "graduation_year")
    private Integer graduationYear;

    @Column(name = "major")
    private String major;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private User user;


}
