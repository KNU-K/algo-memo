package com.knuk.algo_memo.domain.auth.service;

import com.knuk.algo_memo.domain.auth.dto.AuthenticationToken;
import com.knuk.algo_memo.domain.auth.dto.UserDTO;

public interface AuthService {

    /**
     * 사용자 로그인 메서드
     * @param username 사용자 이름
     * @param password 사용자 비밀번호
     * @return 로그인 성공 시 인증 토큰 반환, 실패 시 null 반환
     */
    AuthenticationToken login(String username, String password);

    /**
     * 인증 토큰 갱신 메서드
     * @param oldToken 기존 인증 토큰
     * @return 새로 갱신된 인증 토큰 반환, 갱신 실패 시 null 반환
     */
    AuthenticationToken refresh(AuthenticationToken oldToken);
}