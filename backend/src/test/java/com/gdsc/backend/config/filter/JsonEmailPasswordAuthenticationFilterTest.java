package com.gdsc.backend.config.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.IOException;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@WebMvcTest(JsonEmailPasswordAuthenticationFilter.class)
public class JsonEmailPasswordAuthenticationFilterTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @MockBean
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private ObjectMapper objectMapper;

    private JsonEmailPasswordAuthenticationFilter jsonEmailPasswordAuthenticationFilter;

    @BeforeEach
    public void setUp() {
        jsonEmailPasswordAuthenticationFilter = new JsonEmailPasswordAuthenticationFilter(
                objectMapper, authenticationSuccessHandler, authenticationFailureHandler);
        jsonEmailPasswordAuthenticationFilter.setAuthenticationManager(authenticationManager);
    }

    @Test
    public void testSuccessfulAuthentication() throws Exception {
        String email = "test@example.com";
        String password = "password";
        String requestBody = objectMapper.writeValueAsString(Map.of("email", email, "password", password));

        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);

        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .contentType("application/json")
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(authenticationSuccessHandler, times(1)).onAuthenticationSuccess(any(), any(), any());
    }

    @Test
    public void testFailedAuthentication() throws Exception {
        String email = "test@example.com";
        String password = "wrongpassword";
        String requestBody = objectMapper.writeValueAsString(Map.of("email", email, "password", password));

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new AuthenticationException("Authentication failed") {});

        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .contentType("application/json")
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isForbidden());

        verify(authenticationFailureHandler, times(1)).onAuthenticationFailure(any(), any(), any());
    }

    @Test
    public void testUnsupportedContentType() throws Exception {
        String email = "test@example.com";
        String password = "password";
        String requestBody = objectMapper.writeValueAsString(Map.of("email", email, "password", password));

        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .contentType("text/plain")
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
