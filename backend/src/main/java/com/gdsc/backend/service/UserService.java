package com.gdsc.backend.service;

import com.gdsc.backend.domain.SiteUser;
import com.gdsc.backend.dto.AddUserRequest;
import com.gdsc.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor //final or @NotNull이 붙은 필드의 생성자 추가
@Service
public class UserService {

    private final UserRepository userRepository;

    public SiteUser save(AddUserRequest request){
        return userRepository.save(request.toEntity());
    }
}
