package com.gdsc.backend.repository;

import com.gdsc.backend.domain.SiteUser;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @DisplayName("회원가입 성공")
    @Test
    public void signup_save_성공() throws Exception{
        //given
        SiteUser siteUser = SiteUser.builder()
                .email("optimuslove0223@naver.com")
                .name("신동혁").nickname("신라면")
                .password("1234")
                .birthDate(LocalDate.of(2000, 1, 1)).profileImage("http://ex.com")
                .build();

        //when
        SiteUser savedsiteUser = userRepository.save(siteUser);

        //then
        SiteUser findsiteUser = userRepository.findById(savedsiteUser.getUserID()).orElseThrow(() -> new RuntimeException("저장된 회원이 없습니다"));

        assertThat (findsiteUser).isSameAs(savedsiteUser);
    }

}