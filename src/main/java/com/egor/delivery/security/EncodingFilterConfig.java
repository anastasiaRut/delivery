package com.egor.delivery.security;

import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EncodingFilterConfig extends CharacterEncodingFilter {
    @Override
    public void doFilterInternal(HttpServletRequest httpRequest, HttpServletResponse httpResponse, FilterChain chain)
            throws IOException, ServletException {
        httpRequest.setCharacterEncoding("UTF-8");
        httpResponse.setContentType("text/html; charset=UTF-8");
        chain.doFilter(httpRequest, httpResponse);
    }
}

