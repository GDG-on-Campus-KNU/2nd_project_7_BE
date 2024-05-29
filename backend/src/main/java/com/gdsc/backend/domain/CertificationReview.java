package com.gdsc.backend.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CertificationReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 1씩 증가
    @Column(name = "CertificationReviewID", updatable = false)
    private Long CertificationReviewID;

    @Column(name = "content")
    private String content;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @Column(name = "createdAt")
    private String createdAt;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private SiteUser siteUser;

    @ManyToOne
    @JoinColumn(name = "CertificationID")
    private Certification certification;

    @Builder
    public CertificationReview(String content, Integer rating, String createdAt){
        this.content = content;
        this.rating = rating;
        this.createdAt = createdAt;
    }




}
