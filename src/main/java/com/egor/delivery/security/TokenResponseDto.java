package com.egor.delivery.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenResponseDto {
    private String token;
    private String role;
    private Long userId;
    private String type = "Bearer";

    public TokenResponseDto(String accessToken, String role, Long userId) {
        this.token = accessToken;
        this.role = role;
        this.userId = userId;
    }
}

