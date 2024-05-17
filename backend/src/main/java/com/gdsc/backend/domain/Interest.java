package com.gdsc.backend.domain;

import jakarta.persistence.*;
import lombok.Builder;

public class Interest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interestID", updatable = false)
    private Long interestID;

    @Column(name = "type")
    private String type;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private User user;

    @Builder
    public Interest(String type){
        this.type = type;
    }
}
