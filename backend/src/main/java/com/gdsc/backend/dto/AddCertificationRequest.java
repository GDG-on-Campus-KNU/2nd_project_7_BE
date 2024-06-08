package com.gdsc.backend.dto;

import com.gdsc.backend.domain.Certification;
import com.gdsc.backend.domain.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class AddCertificationRequest {

    private SiteUser siteUser;

    private Long certificationId;

    private LocalDate examDate;

    private LocalDate expireDate;

    private Integer studyPeriod;

}
