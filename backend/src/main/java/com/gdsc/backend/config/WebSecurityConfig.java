package com.gdsc.backend.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdsc.backend.config.filter.JsonEmailPasswordAuthenticationFilter;
import com.gdsc.backend.config.handler.LoginFailureHandler;
import com.gdsc.backend.config.handler.LoginSuccessJWTProvideHandler;
import com.gdsc.backend.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig{
    private final LoginService userService;
    private final ObjectMapper objectMapper;
    // 특정 HTTP 요청에 대한 웹 기반 보안 구성
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        JsonEmailPasswordAuthenticationFilter jsonFilter = new JsonEmailPasswordAuthenticationFilter(objectMapper);
        jsonFilter.setAuthenticationManager(authenticationManager(http, bCryptPasswordEncoder(), userService));

        http
                .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                        .requestMatchers(new AntPathRequestMatcher("/**")).permitAll()
                )
                .formLogin((formLogin) -> formLogin
                        .loginPage("/login")
                        .defaultSuccessUrl("/mainpage")
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                        .permitAll()
                )
                .csrf((csrf) -> csrf
                        .ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")))
                .headers((headers) -> headers
                        .addHeaderWriter(new XFrameOptionsHeaderWriter(
                                XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
                .addFilterAt(jsonFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

    // 인증 관리자 관련 설정
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, LoginService loginService) throws Exception{
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userService)
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
    }

    // 패스워드 인코더로 사용할 빈 등록
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public AuthenticationManager authenticationManager() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(bCryptPasswordEncoder());
//        provider.setUserDetailsService(userService);
//        return new ProviderManager(provider);
//    }

    @Bean
    public LoginSuccessJWTProvideHandler loginSuccessJWTProvideHandler(){
        return new LoginSuccessJWTProvideHandler();
    }

    @Bean
    public LoginFailureHandler loginFailureHandler(){
        return new LoginFailureHandler();
    }

    @Bean
    public JsonEmailPasswordAuthenticationFilter jsonUsernamePasswordLoginFilter(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, LoginService loginService) throws Exception {
        JsonEmailPasswordAuthenticationFilter jsonEmailPasswordLoginFilter = new JsonEmailPasswordAuthenticationFilter(objectMapper);
        // 필요한 파라미터를 전달하여 올바른 authenticationManager 메소드 호출
        jsonEmailPasswordLoginFilter.setAuthenticationManager(authenticationManager(http, bCryptPasswordEncoder, loginService));
        jsonEmailPasswordLoginFilter.setAuthenticationSuccessHandler(loginSuccessJWTProvideHandler());
        jsonEmailPasswordLoginFilter.setAuthenticationFailureHandler(loginFailureHandler());
        return jsonEmailPasswordLoginFilter;
    }


}
