package com.gdsc.backend.controller;

import com.gdsc.backend.config.jwt.TokenProvider;
import com.gdsc.backend.domain.RefreshToken;
import com.gdsc.backend.domain.SiteUser;
import com.gdsc.backend.repository.RefreshTokenRepository;
import com.gdsc.backend.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");

        try {
            // 사용자 인증
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );

            // 인증된 사용자 가져오기
            SiteUser user = (SiteUser) authentication.getPrincipal();

            // JWT 액세스 토큰 생성
            String accessToken = tokenProvider.generateToken(user, Duration.ofHours(1)); // 액세스 토큰 유효 기간 설정

            // 리프레시 토큰 생성
            String refreshToken = tokenProvider.generateToken(user, Duration.ofDays(30)); // 리프레시 토큰 유효 기간 설정

            // 기존 리프레시 토큰 삭제
            refreshTokenRepository.deleteById(user.getUserID());

            // 새 리프레시 토큰 저장
            RefreshToken newRefreshToken = new RefreshToken(user.getUserID(), refreshToken);
            refreshTokenRepository.save(newRefreshToken);

            // 응답으로 access 토큰 과 refresh 토큰 반환
            Map<String, String> response = new HashMap<>();
            response.put("accessToken", accessToken);
            response.put("refreshToken", refreshToken);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (AuthenticationException e) {
            // 로그인 실패 시 예외 처리
            Map<String, String> response = new HashMap<>();
            response.put("error", "Invalid email or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "Login page"; // 실제로는 로그인 페이지로 리디렉션하거나 로그인 폼을 반환
    }
}
