package com.gdsc.backend.domain;

import jakarta.persistence.*;

@Entity
public class CompanyCertification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 1씩 증가
    @Column(name = "CompanyCertificationID", updatable = false)
    private Long CompanyCertificationID;
    @ManyToOne
    @JoinColumn(name = "CompanyID", nullable = false)
    private Company company;

    @ManyToOne
    @JoinColumn(name = "CertificationID", nullable = false)
    private Certification certification;
}
