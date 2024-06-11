package com.gdsc.backend.repository;

import com.gdsc.backend.domain.CertificationReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificationReviewRepository extends JpaRepository<CertificationReview, Long> {
}