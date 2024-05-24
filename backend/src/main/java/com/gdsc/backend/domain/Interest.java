package com.gdsc.backend.domain;

import jakarta.persistence.*;
import lombok.Builder;

public class Interest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "InterestID", updatable = false)
    private Long InterestID;

    @Column(name = "type")
    private String type;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private SiteUser siteUser;

    @Builder
    public Interest(String type){
        this.type = type;
    }
}
