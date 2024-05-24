package com.gdsc.backend.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ScheduleID", updatable = false)
    private Long ScheduleID;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private User user;
}
