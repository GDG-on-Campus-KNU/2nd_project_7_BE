package com.gdsc.backend.dto;

import com.gdsc.backend.domain.Certification;
import com.gdsc.backend.domain.SiteUser;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddCertificationReivewRequest {
    private String content;

    private Integer rating;

    private String createdAt;

    private SiteUser siteUser;

    private Certification certification;
}
