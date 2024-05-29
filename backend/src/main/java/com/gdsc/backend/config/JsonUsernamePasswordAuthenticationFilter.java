package com.gdsc.backend.config;

import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

// formLogin 필터를 커스터마이징하여 데이터 처리 방식을 http form 에서 json 으로 변경..
public class JsonUsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private static final String DEFAULT_LOGIN_REQUEST_URL = "/login";
    private static final String HTTP_METHOD = "POST";
    private static final String CONTENT_TYPE = "application/json";
}
