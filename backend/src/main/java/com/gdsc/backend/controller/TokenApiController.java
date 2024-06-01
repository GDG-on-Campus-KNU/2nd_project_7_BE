package com.gdsc.backend.controller;

import com.gdsc.backend.dto.CreateAccessTokenRequest;
import com.gdsc.backend.dto.CreateAccessTokenResponse;
import com.gdsc.backend.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class TokenApiController {
    private final TokenService tokenService;

    @PostMapping("/token")
    public ResponseEntity<?> createNewAccessToken(@RequestBody CreateAccessTokenRequest request) {
        try {
            // 새로운 액세스 토큰 생성
            String newAccessToken = tokenService.createNewAccessToken(request.getRefreshToken());

            // 새로운 액세스 토큰을 응답으로 반환
            return ResponseEntity.status(HttpStatus.CREATED).body(new CreateAccessTokenResponse(newAccessToken));
        } catch (IllegalAccessException e) {
            // 리프레시 토큰이 유효하지 않은 경우 401 Unauthorized 응답 반환
            String errorMessage = "리프레시 토큰이 유효하지 않음";
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);
        }
    }

}
