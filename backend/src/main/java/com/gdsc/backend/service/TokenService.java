package com.gdsc.backend.service;

import com.gdsc.backend.config.jwt.TokenProvider;
import com.gdsc.backend.domain.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final SignupService signupService;

    //전달받은 리프레시 토큰으로 토큰 유효성 검사 실시 + 유효한 토큰일 때 리프레시 토큰으로 사용자ID 를 찾음
    // 이후 토큰 제공자의 generateToken() 메서드 호출하여 새로운 액세스 토큰 생성
    public String createNewAccessToken(String refreshToken) throws IllegalAccessException {
        if(!tokenProvider.validToken(refreshToken)){
            throw new IllegalAccessException("Unexpected Token");
        }

        Long userID = refreshTokenService.findByRefreshToken(refreshToken).getUserID();
        SiteUser user = signupService.findById(userID);

        return tokenProvider.generateToken(user, Duration.ofHours(2));

    }
}
