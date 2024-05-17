package com.gdsc.backend.domain;

import jakarta.persistence.*;
import lombok.Builder;

public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "educationID", updatable = false)
    private Long educationID;

    @Column(name = "educationLevel", nullable = false)
    private String educationLevel;

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

    @Builder
    public Education(String educationLevel, String status, Double grade, Integer graduationYear, String major){
        this.educationLevel = educationLevel;
        this.status = status;
        this.grade = grade;
        this.graduationYear = graduationYear;
        this.major = major;
    }


}
