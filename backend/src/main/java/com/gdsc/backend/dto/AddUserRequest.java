package com.gdsc.backend.dto;

import com.gdsc.backend.domain.SiteUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@NoArgsConstructor // 기본 생성자 추가
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자 추가
@Getter
public class AddUserRequest {
    private String email;
    private String password;
    private String name;
    private String nickname;
    private LocalDate birthDate;
    private String profileImage;

//    public SiteUser toEntity(){
//        return SiteUser.builder()
//                .email(email)
//                .password(password)
//                .name(name)
//                .nickname(nickname)
//                .birthDate(birthDate)
//                .profileImage(profileImage)
//                .build();
//    }x

}
