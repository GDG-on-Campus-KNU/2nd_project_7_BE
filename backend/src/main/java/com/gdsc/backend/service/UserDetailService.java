package com.gdsc.backend.service;

import com.gdsc.backend.domain.SiteUser;
import com.gdsc.backend.dto.AddUserRequest;
import com.gdsc.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor //final or @NotNull이 붙은 필드의 생성자 추가
@Service

//스프링 시큐리티에서 사용자 정보 가져오는 인터페이스
public class UserDetailService implements UserDetailsService{
    private final UserRepository userRepository;

    @Override
    public SiteUser loadUserByUsername(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException((email)));
    }
}
