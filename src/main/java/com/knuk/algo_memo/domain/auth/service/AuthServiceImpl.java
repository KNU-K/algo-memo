package com.knuk.algo_memo.domain.auth.service;

import com.knuk.algo_memo.domain.user.dto.UserDTO;
import com.knuk.algo_memo.domain.user.service.UserService;

import com.knuk.algo_memo.domain.auth.dto.AuthenticationToken;

//import com.knuk.algo_memo.global.jwt.JwtProvider;
import com.knuk.algo_memo.global.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final JwtProvider jwtProvider;
    private final UserService userService;
    @Override
    public AuthenticationToken login(String email, String password) {
        UserDTO user = userService.findByEmail(email);
        if(!user.getPassword().equals(password)) throw new RuntimeException("비밀번호가 일치하지않습니다.");


        /* 유저에 대한 값을 넣어야 함 */
        String accessToken = jwtProvider.generateAccessToken(user.getName());
        String refreshToken = jwtProvider.generateRefreshToken(user.getName());
        return new AuthenticationToken(accessToken, refreshToken);
    }
    @Override
    public AuthenticationToken refresh(AuthenticationToken oldToken) {
        /**
         * refresh token 이 redis에 있는 확인
         *
         */
        return null;
    }
}
