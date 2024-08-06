package com.knuk.algo_memo.domain.auth.service;

import com.knuk.algo_memo.domain.auth.dto.AuthenticationToken;
import com.knuk.algo_memo.domain.auth.dto.UserDTO;

public class AuthServiceImpl implements AuthService{
    @Override
    public AuthenticationToken login(String username, String password) {
        return null;
    }

    @Override
    public boolean join(UserDTO user) {
        return false;
    }

    @Override
    public AuthenticationToken refresh(AuthenticationToken oldToken) {
        return null;
    }
}
