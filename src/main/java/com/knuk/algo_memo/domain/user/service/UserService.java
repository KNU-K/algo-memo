package com.knuk.algo_memo.domain.user.service;

import com.knuk.algo_memo.domain.user.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserDto findByUsername(String username);
}
