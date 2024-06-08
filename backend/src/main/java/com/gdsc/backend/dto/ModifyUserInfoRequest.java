package com.gdsc.backend.dto;

import jakarta.persistence.Column;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ModifyUserInfoRequest {

    private String email;

    private String name;

    private String nickname;

    private LocalDate birthDate;

    private String profileImage;

}
