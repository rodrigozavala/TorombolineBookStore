package com.torombolinebookstore.apigw.t_book_gw.entry_point;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class LoginRedirectAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final String loginUrl;

    public LoginRedirectAuthenticationEntryPoint(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.sendRedirect(loginUrl);
    }
}
