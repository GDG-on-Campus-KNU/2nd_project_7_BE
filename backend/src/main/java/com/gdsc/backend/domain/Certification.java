package com.gdsc.backend.domain;

import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Certification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 1씩 증가
    @Column(name = "CertificationID", updatable = false)
    private Long CertificationID;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "overview", nullable = false)
    private String overview;

    @Column(name = "examContent", nullable = false)
    private String examContent;

    @OneToMany(mappedBy = "certification")
    private List<CompanyCertification> companies = new ArrayList<>();

    @OneToMany(mappedBy = "certification")
    private List<CertificationReview> certificationReviews = new ArrayList<>();

    @OneToMany(mappedBy = "certification")
    private List<CertificationFavorite> certificationFavorites = new ArrayList<>();

    @OneToMany(mappedBy = "certification")
    private List<Studypost> studyposts = new ArrayList<>();
    @Builder
    public Certification(String name, String overview, String examContent){
        this.name = name;
        this.overview = overview;
        this.examContent = examContent;
    }

    public Certification() {

    }
}
