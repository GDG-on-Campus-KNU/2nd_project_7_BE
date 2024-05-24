package com.gdsc.backend.domain;

import jakarta.persistence.*;

@Entity
public class StudypostFavorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 1씩 증가
    @Column(name = "StudypostCommentID", updatable = false)
    private Long StudypostCommentID;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "StudypostID")
    private Studypost studypost;
}
