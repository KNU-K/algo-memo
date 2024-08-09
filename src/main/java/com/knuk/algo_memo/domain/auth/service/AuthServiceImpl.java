package com.knuk.algo_memo.domain.auth.service;

import com.knuk.algo_memo.domain.user.dto.UserDto;
import com.knuk.algo_memo.domain.user.service.UserService;

import com.knuk.algo_memo.domain.auth.dto.AuthenticationToken;

import com.knuk.algo_memo.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final JwtProvider jwtProvider;
    private final UserService userService;
    @Override
    public AuthenticationToken login(String username, String password) {
        try {

            UserDto user = userService.findByUsername(username);
            if(!user.getPassword().equals(password)) throw new RuntimeException("비밀번호가 일치하지않습니다.");

        }catch(RuntimeException err){

        }

        /* 유저에 대한 값을 넣어야 함 */
        String accessToken = jwtProvider.generateAccessToken(null);
        String refreshToken = jwtProvider.generateRefreshToken(null);
        return new AuthenticationToken(accessToken, refreshToken);
    }

    @Override
    public AuthenticationToken refresh(AuthenticationToken oldToken) {
        return null;
    }
}
