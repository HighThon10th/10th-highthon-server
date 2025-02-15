package com.highthon.global.security.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class TokenDto {
    private String accessToken;
    private int accessTokenExp;
}
