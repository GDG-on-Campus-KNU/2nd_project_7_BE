package com.gdsc.backend.config;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class WebSecurityConfigTest {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @DisplayName("비밀번호 암호화 테스트")
    @Test
    public void 비밀번호_암호화_테스트() throws Exception {
        // given
        String password = "호호호호";

        // when
        String encodePassword = passwordEncoder.encode(password);

        // then
        assertThat(passwordEncoder.matches(password, encodePassword)).isTrue();
    }
}
