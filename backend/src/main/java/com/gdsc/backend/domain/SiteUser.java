package com.gdsc.backend.domain;

import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SiteUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 1씩 증가
    @Column(name = "UserID", updatable = false)
    private Long UserID;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "birthDate", nullable = false)
    private LocalDate birthDate;

    @Column(name = "profileImage")
    private String profileImage;

    @OneToMany(mappedBy = "siteUser")
    private List<CertificationReview> certificationReviews = new ArrayList<>();

    @OneToMany(mappedBy = "siteUser")
    private List<CertificationFavorite> certificationFavorites = new ArrayList<>();

    @OneToMany(mappedBy = "siteUser")
    private List<Studypost> studyposts = new ArrayList<>();

    @OneToMany(mappedBy = "siteUser")
    private List<StudypostComment> studypostcomments = new ArrayList<>();

    @OneToMany(mappedBy = "siteUser")
    private List<StudypostFavorite> studypostfavorites = new ArrayList<>();

    @Builder
    public SiteUser(String email, String password, String name, String nickname, LocalDate birthDate, String profileImage){
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.birthDate = birthDate;
        this.profileImage = profileImage;
    }

    public SiteUser() {

    }
}
