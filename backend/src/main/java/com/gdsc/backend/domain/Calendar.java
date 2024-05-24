package com.gdsc.backend.domain;

import jakarta.persistence.*;

@Entity
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CalendarID", updatable = false)
    private Long CalendarID;
    @ManyToOne
    @JoinColumn(name = "UserID")
    private SiteUser siteUser;

    @ManyToOne
    @JoinColumn(name = "CertificationID")
    private Certification certification;

    @ManyToOne
    @JoinColumn(name = "ScheduleID")
    private Schedule schedule;
}
