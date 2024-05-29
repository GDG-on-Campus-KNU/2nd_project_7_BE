package com.gdsc.backend.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    private SiteUser siteUser;
}
