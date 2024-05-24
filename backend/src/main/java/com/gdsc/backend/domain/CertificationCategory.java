package com.gdsc.backend.domain;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
public class CertificationCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 1씩 증가
    @Column(name = "CertificationCategoryID", updatable = false)
    private Long CertificationCategoryID;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "CertificationID")
    private Certification certification;

    @Builder
    public CertificationCategory(String name){
        this.name = name;
    }


}
