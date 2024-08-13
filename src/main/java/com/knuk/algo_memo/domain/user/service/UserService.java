package com.knuk.algo_memo.domain.user.service;

import com.knuk.algo_memo.domain.user.dto.CreateUserCommand;
import com.knuk.algo_memo.domain.user.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserDTO findByEmail(String email);
    UserDTO findById(Long id);
    UserDTO createUser(CreateUserCommand command);
}
