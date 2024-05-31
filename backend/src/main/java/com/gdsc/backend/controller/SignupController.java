package com.gdsc.backend.controller;

import com.gdsc.backend.dto.UserFormDto;
import com.gdsc.backend.service.SignupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // JSON 형태로 데이터를 주고받기 위해 RestController 사용
@RequestMapping("/signup")
@RequiredArgsConstructor // final이나 @NonNull인 필드 값만 파라미터로 받는 생성자를 만들어줌
public class SignupController {

    private final SignupService signupService;

    @PostMapping // 회원가입을 위한 POST 메서드
    public ResponseEntity<Long> registerUser(@RequestBody UserFormDto userFormDto) {
        Long userId = signupService.save(userFormDto);
        return ResponseEntity.ok(userId); // 회원가입 처리 후, 생성된 사용자의 ID를 반환
    }
}
