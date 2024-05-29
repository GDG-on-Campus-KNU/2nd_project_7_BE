package com.gdsc.backend.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AcquiredCertification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 1씩 증가
    @Column(name = "AcquiredCertificationID", updatable = false)
    private Long AcquiredCertificationID;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private SiteUser siteUser;

    @ManyToOne
    @JoinColumn(name = "CertificationID")
    private Certification certification;

    @Column(name = "examDate")
    private LocalDate examDate;

    @Column(name = "expireDate")
    private LocalDate expireDate;

    @Column(name = "studyPeriod")
    private Integer studyPeriod;

    @Builder
    public AcquiredCertification(LocalDate examDate, LocalDate expireDate, Integer studyPeriod){
        this.examDate = examDate;
        this.expireDate = expireDate;
        this.studyPeriod = studyPeriod;
    }
}
