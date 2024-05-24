package com.gdsc.backend.domain;

import jakarta.persistence.*;

@Entity
public class CertificationFavorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 1씩 증가
    @Column(name = "CertificationFavoriteID", updatable = false)
    private Long CertificationFavoriteID;
    @ManyToOne
    @JoinColumn(name = "UserID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "CertificationID")
    private Certification certification;
}
