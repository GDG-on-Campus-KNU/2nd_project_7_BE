package com.gdsc.backend.repository;

import com.gdsc.backend.domain.AcquiredCertification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcquiredCertificationRepository extends JpaRepository<AcquiredCertification, Long> {
}
