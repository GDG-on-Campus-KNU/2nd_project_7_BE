package com.gdsc.backend.domain;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Studypost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 1씩 증가
    @Column(name = "StudypostID", updatable = false)
    private Long StudypostID;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private SiteUser siteUser;

    @ManyToOne
    @JoinColumn(name = "CertificationID")
    private Certification certification;

    @OneToMany(mappedBy = "studypost")
    private List<StudypostComment> studypostcomments = new ArrayList<>();

    @OneToMany(mappedBy = "studypost")
    private List<StudypostFavorite> studypostfavorites = new ArrayList<>();

    @Builder
    public Studypost(String title, String content){
        this.title = title;
        this.content = content;
    }
}
