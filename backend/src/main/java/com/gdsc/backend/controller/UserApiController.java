package com.gdsc.backend.controller;

import com.gdsc.backend.domain.CertificationReview;
import com.gdsc.backend.domain.SiteUser;
import com.gdsc.backend.dto.*;
import com.gdsc.backend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController("/api/user")
public class UserApiController {
    private final UserService userService;

    @PostMapping()

    public ResponseEntity<SiteUser> addUser(@RequestBody AddUserRequest request){
        SiteUser savedSiteUser = userService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedSiteUser);
        // 요청된 자원이 성공적으로 생성됨 + 저장된 내용 응답 객체에 담아 전송
    }

    @GetMapping("/{userId}")
    @Operation(summary = "개인정보 불러오기")
    public UserInfoDto getUserInfo(@PathVariable Long userId) {
        return userService.getUserInfo(userId);
    }

    @PutMapping("/modify/{userId}")
    @Operation(summary = "사용자 정보 수정")
    public ModifyUserResponse modifyUser(@PathVariable Long userId, @RequestBody ModifyUserInfoRequest requestDTO) {
        return userService.modifyUser(userId, requestDTO);
    }

    @PostMapping("/certification")
    @Operation(summary = "취득한 자격증 추가")
    public AddUserCertificationResponse addCertification(@RequestBody AddUserCertificationRequest request) {
        return userService.addCertification(request);
    }


    @PostMapping("/review")
    @Operation(summary = "취득한 자격증 리뷰 추가")
    public CertificationReview addCertificationReview(@RequestBody AddCertificationReivewRequest request) {
        return userService.addCertificationReview(request);
    }

    @PutMapping("/certification/{userId}/{acquiredCertificationId}")
    @Operation(summary = "취득한 자격증 정보 수정")
    public ResponseEntity<String> modifyUserCertification(
            @PathVariable Long userId,
            @PathVariable Long acquiredCertificationId,
            @RequestBody ModifyUserCertificationRequest request) {

        boolean isModified = userService.modifyUserCertification(userId, acquiredCertificationId, request);
        if (isModified) {
            return ResponseEntity.ok("User certification modified successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User certification not found or does not belong to the user.");
        }
    }

    @DeleteMapping("/certification")
    @Operation(summary = "습득한 자격증 삭제")
    public ResponseEntity<String> deleteCertification(@PathVariable Long certificationId) {
        boolean isDeleted = userService.deleteCertification(certificationId);
        if (isDeleted) {
            return ResponseEntity.ok("Certification deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Certification not found.");
        }
    }

}
