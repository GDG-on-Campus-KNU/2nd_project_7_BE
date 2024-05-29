package com.gdsc.backend.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.graph.CannotContainSubGraphException;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExamSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ExamScheduleID", updatable = false)
    private Long ExamScheduleID;

    @Column(name = "examDate", nullable = false)
    private LocalDate examDate;

    @Column(name = "registrationStart", nullable = false)
    private LocalDate registrationStart;

    @Column(name = "registrationEnd", nullable = false)
    private LocalDate registrationEnd;

    @Column(name = "announcementDate", nullable = false)
    private LocalDate announcementDate;

    @ManyToOne
    @JoinColumn(name = "CertificationID", nullable = false)
    private Certification certification;

    @Builder
    public ExamSchedule(Certification certification, LocalDate examDate, LocalDate registrationStart, LocalDate registrationEnd, LocalDate announcementDate) {
        this.certification = certification;
        this.examDate = examDate;
        this.registrationStart = registrationStart;
        this.registrationEnd = registrationEnd;
        this.announcementDate = announcementDate;
    }
}
