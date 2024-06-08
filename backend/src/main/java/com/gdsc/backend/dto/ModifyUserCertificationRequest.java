package com.gdsc.backend.dto;

import lombok.Getter;

import java.time.LocalDate;
@Getter
public class ModifyUserCertificationRequest {

    private LocalDate examDate;

    private LocalDate expireDate;

    private Integer studyPeriod;
}
