package com.gdsc.backend.service;

import com.gdsc.backend.domain.SiteUser;
import com.gdsc.backend.dto.SignupRequest;
import com.gdsc.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

//AddUserRequest 객체를 인수로 받는 회원 정보 추가 메서드
@RequiredArgsConstructor
@Service
public class SignupService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(SignupRequest dto){
        return userRepository.save(SiteUser.builder()
                .email(dto.getEmail())
                //패스워드 암호화
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                        .name(dto.getName())
                        .nickname(dto.getNickname())
                        .birthDate(dto.getBirthDate())
                        .profileImage(dto.getProfileImage())
                .build()).getUserID();

    }
    public SiteUser findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }
}
