package com.gdsc.backend.repository;

import com.gdsc.backend.domain.CertificationCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificationCategoryRepository extends JpaRepository<CertificationCategory, Long> {
}
