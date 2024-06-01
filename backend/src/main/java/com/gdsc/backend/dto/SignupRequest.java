package com.gdsc.backend.dto;

import com.gdsc.backend.domain.SiteUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@NoArgsConstructor(force = true)
@Getter
public class SignupRequest {

    private String email;
    private String name;
    private String nickname;
    private String password;
    private LocalDate birthDate;
    private String profileImage;

    // 생성자 정의
    public SignupRequest(String email, String name, String nickname, String password, LocalDate birthDate, String profileImage) {
        this.email = email;
        this.name = name;
        this.nickname = nickname;
        this.password = password;
        this.birthDate = birthDate;
        this.profileImage = profileImage;
    }

}
