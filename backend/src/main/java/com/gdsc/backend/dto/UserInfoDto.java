package com.gdsc.backend.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UserInfoDto {
    private String email;

    private String name;

    private String nickname;

    private LocalDate birthDate;

    private String profileImage;


    public UserInfoDto(String email, String name, String nickname, LocalDate birthDate, String profileImage) {
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.birthDate = birthDate;
        this.profileImage = profileImage;
    }
}
