package com.gdsc.backend.repository;

import com.gdsc.backend.domain.Studypost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyPostRepository extends JpaRepository<Studypost, Long> {
}
