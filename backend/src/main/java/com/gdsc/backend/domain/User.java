package com.gdsc.backend.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 1씩 증가
    @Column(name = "userID", updatable = false)
    private Long userID;

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



}