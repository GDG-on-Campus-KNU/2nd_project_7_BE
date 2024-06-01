package com.gdsc.backend.controller;

import com.gdsc.backend.dto.SignupRequest;
import com.gdsc.backend.service.SignupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class SignupController {

    private final SignupService signupService;

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody SignupRequest signupRequest) {
        try {
            Long userId = signupService.save(signupRequest);
            String responseBody = "{\"state\": \"success\"}";
            return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
        } catch (Exception e) {
            String responseBody = "{\"state\": \"fail\"}";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
        }
    }

    @GetMapping("/signup")
    public String getSignupPage() {
        return "Signup page";
    }
}
