package com.knuk.algo_memo.global.jwt;

import lombok.Builder;
import lombok.Getter;

@Getter
public class JwtTokenInfo {

    private final Long userId;
    @Builder
    public JwtTokenInfo(Long userId) {
        this.userId = userId;
    }
}