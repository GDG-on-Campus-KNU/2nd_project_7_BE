package com.gdsc.backend.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdsc.backend.domain.SiteUser;
import com.gdsc.backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class JsonEmailPasswordAuthenticationFilterTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    ObjectMapper objectMapper = new ObjectMapper();

    private static String KEY_EMAIL = "email";
    private static String KEY_PASSWORD = "password";
    private static String USEREMAIL = "ex@naver.com";
    private static String PASSWORD = "123456789";

    private static String LOGIN_URL = "/login";
    @BeforeEach
    private void init(){
        userRepository.save(SiteUser.builder()
                        .email(USEREMAIL)
                        .password(bCryptPasswordEncoder.encode(PASSWORD))
                        .name("shin")
                        .nickname("shsh")
                        .birthDate(LocalDate.of(1990, 1, 1)) // 예시 생년월일
                        .profileImage("example.com")
                        .build());
    }


    private Map<String, String> getUsernamePasswordMap(String email, String password) {
        Map<String, String> map = new HashMap<>();
        map.put(KEY_EMAIL, email);
        map.put(KEY_PASSWORD, password);
        return map;
    }

    private ResultActions perform(String url, MediaType mediaType, Map<String, String> usernamePasswordMap) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
                .post(url)
                .contentType(mediaType)
                .content(objectMapper.writeValueAsString(usernamePasswordMap)));
    }

    @Test
    public void 로그인_성공() throws Exception{
        //given
        Map<String, String> map = getUsernamePasswordMap(USEREMAIL, PASSWORD);

        //when, then
        MvcResult result = perform(LOGIN_URL, APPLICATION_JSON, map)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
}