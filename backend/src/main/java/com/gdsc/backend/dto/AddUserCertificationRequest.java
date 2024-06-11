package com.gdsc.backend.dto;

import com.gdsc.backend.domain.SiteUser;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class AddUserCertificationRequest {

    private SiteUser siteUser;

    private Long certificationId;

    private LocalDate examDate;

    private LocalDate expireDate;

    private Integer studyPeriod;

}
