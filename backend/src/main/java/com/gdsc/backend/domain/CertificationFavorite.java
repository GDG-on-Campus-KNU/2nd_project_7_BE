package com.gdsc.backend.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CertificationFavorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 1씩 증가
    @Column(name = "CertificationFavoriteID", updatable = false)
    private Long CertificationFavoriteID;
    @ManyToOne
    @JoinColumn(name = "UserID")
    private SiteUser siteUser;

    @ManyToOne
    @JoinColumn(name = "CertificationID")
    private Certification certification;
}
