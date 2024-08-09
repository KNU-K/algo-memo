package com.knuk.algo_memo.domain.auth.dto;
import lombok.Data;

@Data
public class AuthenticationToken {
    private final String accessToken;
    private final String refreshToken;
    public AuthenticationToken(String accessToken, String refreshToken) {

        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
