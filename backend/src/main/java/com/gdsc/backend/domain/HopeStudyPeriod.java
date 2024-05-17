package com.gdsc.backend.domain;

import jakarta.persistence.*;

public class HopeStudyPeriod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hopestudyperiodID", updatable = false)
    private Long hopestudyperiodID;

    @Column(name = "hopeMonth")
    private Integer hopeMonth;

    @Column(name = "hopePeriod")
    private Integer hopePeriod;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private User user;
}
