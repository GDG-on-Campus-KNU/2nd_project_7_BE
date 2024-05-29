package com.gdsc.backend.service;

import com.gdsc.backend.domain.SiteUser;
import com.gdsc.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor //final or @NotNull이 붙은 필드의 생성자 추가
@Service

//스프링 시큐리티에서 사용자 정보 가져오는 인터페이스
public class LoginService implements UserDetailsService{
    private final UserRepository userRepository;

    @Override
    public SiteUser loadUserByUsername(String email) throws UsernameNotFoundException {
        SiteUser siteUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException(("아이디가 존재하지 않습니다. 회원가입 해주세요")));

        return SiteUser.builder()
                .email(siteUser.getEmail())
                .password(siteUser.getPassword())
                .name(siteUser.getName())
                .birthDate(siteUser.getBirthDate())
                .nickname(siteUser.getNickname())
                .profileImage(siteUser.getProfileImage())
                .build();

    }
}
