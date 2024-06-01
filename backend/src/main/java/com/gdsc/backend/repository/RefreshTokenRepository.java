package com.gdsc.backend.repository;

import com.gdsc.backend.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByUserID(Long UserID);
    void deleteByUserID(Long userID);
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
