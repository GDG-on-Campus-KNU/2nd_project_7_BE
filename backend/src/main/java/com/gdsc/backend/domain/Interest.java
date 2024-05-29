package com.gdsc.backend.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
