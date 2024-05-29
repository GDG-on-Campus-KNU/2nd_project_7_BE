package com.gdsc.backend.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HopeStudyPeriod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hopestudyperiodID", updatable = false)
    private Long hopestudyperiodID;

    @Column(name = "hopeMonth")
    private Integer hopeMonth;

    @Column(name = "hopePeriod")
    private Integer hopePeriod;

    @ManyToOne
    @JoinColumn(name = "UserID")
    private SiteUser siteUser;

    @Builder
    public HopeStudyPeriod(Integer hopeMonth, Integer hopePeriod){
        this.hopeMonth = hopeMonth;
        this.hopePeriod = hopePeriod;
    }
}
