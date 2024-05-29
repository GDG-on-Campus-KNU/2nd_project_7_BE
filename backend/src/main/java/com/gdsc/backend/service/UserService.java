package com.gdsc.backend.service;

import com.gdsc.backend.domain.SiteUser;
import com.gdsc.backend.dto.AddUserRequest;
import com.gdsc.backend.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

//AddUserRequest 객체를 인수로 받는 회원 정보 추가 메서드
@RequiredArgsConstructor
@Service

public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUserRequest dto){
        return userRepository.save(SiteUser.builder()
                .email(dto.getEmail())
                //패스워드 암호화
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .build()).getUserID();

    }
}
