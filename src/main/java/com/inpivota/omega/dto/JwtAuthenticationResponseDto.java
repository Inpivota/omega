package com.inpivota.omega.dto;

import lombok.Data;

@Data
public class JwtAuthenticationResponseDto {

    private static final String TOKEN_TYPE = "Bearer";
    private String accessToken;

    public JwtAuthenticationResponseDto(final String accessToken) {
        this.accessToken = accessToken;
    }
}
