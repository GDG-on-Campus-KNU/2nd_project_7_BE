package com.gdsc.backend.domain;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
public class StudypostComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 1씩 증가
    @Column(name = "StudypostCommentID", updatable = false)
    private Long StudypostCommentID;

    @Column(name = "ispublic", nullable = false)
    private Boolean ispublic;

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "StudypostID")
    private Studypost studypost;

    @Builder
    public StudypostComment(Boolean ispublic, String content){
        this.ispublic = ispublic;
        this.content = content;
    }

}
