package com.gdsc.backend.controller;

import com.gdsc.backend.domain.SiteUser;
import com.gdsc.backend.dto.AddUserRequest;
import com.gdsc.backend.dto.ModifyUserInfoRequest;
import com.gdsc.backend.dto.ModifyUserResponse;
import com.gdsc.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserApiController {
    private final UserService userService;

    @PostMapping("/api/user")
    public ResponseEntity<SiteUser> addUser(@RequestBody AddUserRequest request){
        SiteUser savedSiteUser = userService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedSiteUser);
        // 요청된 자원이 성공적으로 생성됨 + 저장된 내용 응답 객체에 담아 전송
    }

    @PutMapping("/modify/{userId}")
    public ModifyUserResponse modifyUser(@PathVariable Long userId, @RequestBody ModifyUserInfoRequest requestDTO) {
        return userService.modifyUser(userId, requestDTO);
    }
}
