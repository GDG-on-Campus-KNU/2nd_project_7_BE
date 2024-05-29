package com.gdsc.backend.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 1씩 증가
    @Column(name = "CompanyID", updatable = false)
    private Long CompanyID;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "benefit")
    private String benefit;

    @OneToMany(mappedBy = "company")
    private List<CompanyCertification> certifications = new ArrayList<>();

    @Builder
    public Company(String name, String benefit){
        this.name = name;
        this.benefit = benefit;
    }
}
