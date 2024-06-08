package com.gdsc.backend.service;

import com.gdsc.backend.domain.SiteUser;
import com.gdsc.backend.dto.AddUserRequest;
import com.gdsc.backend.dto.ModifyUserInfoRequest;
import com.gdsc.backend.dto.ModifyUserResponse;
import com.gdsc.backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@RequiredArgsConstructor //final or @NotNull이 붙은 필드의 생성자 추가
@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    public SiteUser save(AddUserRequest request){
        return userRepository.save(request.toEntity());
    }

    @Transactional
    public ModifyUserResponse modifyUser(Long userId, ModifyUserInfoRequest request) {
        Optional<SiteUser> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            SiteUser user = optionalUser.get();
            user.setEmail(request.getEmail());
            user.setName(request.getName());
            user.setNickname(request.getNickname());
            user.setBirthDate(request.getBirthDate());
            user.setProfileImage(request.getProfileImage());
            userRepository.save(user);
            return new ModifyUserResponse(true, "User information updated successfully.");
        } else {
            return new ModifyUserResponse(false, "User not found.");
        }
    }

}
